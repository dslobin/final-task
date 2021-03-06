package by.epam.autoshow.dao.impl;

import by.epam.autoshow.dao.DaoException;
import by.epam.autoshow.dao.SqlColumnName;
import by.epam.autoshow.dao.UserDao;
import by.epam.autoshow.model.User;
import by.epam.autoshow.model.UserRole;
import by.epam.autoshow.model.UserStatus;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private Connection connection;

    private static final String INSERT =
            "INSERT INTO users (username, password, role, status) VALUES (?, ?, ?, ?)";

    private static final String UPDATE =
            "UPDATE users SET password = ?, status = ? WHERE user_id = ?";

    private static final String FIND_ALL =
            "SELECT user_id, username, password, role, status FROM users";

    private static final String FIND_BY_USERNAME =
            "SELECT user_id, username, password, role, status FROM users WHERE username = ?";

    private static final String FIND_BY_USERNAME_AND_PASSWORD =
            "SELECT user_id, username, password, role, status FROM users WHERE username = ? AND password = ?";

    private static final String FIND_BY_ID =
            "SELECT user_id, username, password, role, status FROM users WHERE user_id = ?";

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean insert(User user) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole().name());
            preparedStatement.setString(4, user.getStatus().name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error adding user", e);
        }
        return true;
    }

    @Override
    public Optional<User> findById(long id) throws DaoException {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User(
                            resultSet.getLong(SqlColumnName.USER_ID),
                            resultSet.getString(SqlColumnName.USERNAME),
                            resultSet.getString(SqlColumnName.PASSWORD),
                            UserRole.valueOf(resultSet.getString(SqlColumnName.ROLE)),
                            UserStatus.valueOf(resultSet.getString(SqlColumnName.STATUS))
                    );
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error finding user by id", e);
        }
        return Optional.ofNullable(user);
    }

    public Optional<User> findByUsername(String username) throws DaoException {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USERNAME)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User(
                            resultSet.getLong(SqlColumnName.USER_ID),
                            resultSet.getString(SqlColumnName.USERNAME),
                            resultSet.getString(SqlColumnName.PASSWORD),
                            UserRole.valueOf(resultSet.getString(SqlColumnName.ROLE)),
                            UserStatus.valueOf(resultSet.getString(SqlColumnName.STATUS))
                    );
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error finding user by username", e);
        }
        return Optional.ofNullable(user);
    }

    public Optional<User> authorizeUser(User user) throws DaoException {
        User authorizeUser = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USERNAME_AND_PASSWORD)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    authorizeUser = new User(
                            resultSet.getLong(SqlColumnName.USER_ID),
                            resultSet.getString(SqlColumnName.USERNAME),
                            resultSet.getString(SqlColumnName.PASSWORD),
                            UserRole.valueOf(resultSet.getString(SqlColumnName.ROLE)),
                            UserStatus.valueOf(resultSet.getString(SqlColumnName.STATUS))
                    );
                }
            }
        } catch (SQLException e) {
            throw new DaoException("User authorization error", e);
        }
        return Optional.ofNullable(authorizeUser);
    }

    @Override
    public User update(User user) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getStatus().name());
            preparedStatement.setLong(3, user.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error updating user", e);
        }
        return user;
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> userList = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong(SqlColumnName.USER_ID),
                        resultSet.getString(SqlColumnName.USERNAME),
                        resultSet.getString(SqlColumnName.PASSWORD),
                        UserRole.valueOf(resultSet.getString(SqlColumnName.ROLE)),
                        UserStatus.valueOf(resultSet.getString(SqlColumnName.STATUS))
                );
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException("Error finding users", e);
        }
        return userList;
    }
}