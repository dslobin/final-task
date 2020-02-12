package by.epam.autoshow.dao.impl;

import by.epam.autoshow.dao.DaoException;
import by.epam.autoshow.dao.SqlColumnName;
import by.epam.autoshow.dao.UserDao;
import by.epam.autoshow.model.User;
import by.epam.autoshow.model.UserRole;
import by.epam.autoshow.model.UserStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("Duplicates")
public class UserDaoImpl implements UserDao {
    private Connection connection;
    private static final Logger logger = LogManager.getLogger();

    private static final String INSERT =
            "INSERT INTO users (username, password, role, status) VALUES (?, ?, ?, ?)";

    private static final String UPDATE =
            "UPDATE users SET username = ?, password = ?, role = ?, status = ?";

    private static final String DELETE =
            "DELETE FROM users WHERE user_id = ?";

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
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole().name());
            preparedStatement.setString(4, user.getStatus().name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
        return true;
    }

    @Override
    public Optional<User> findById(long id) throws DaoException {
        User user = new User();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setUserId(resultSet.getLong(SqlColumnName.USER_ID));
                user.setUsername(resultSet.getString(SqlColumnName.USERNAME));
                user.setPassword(resultSet.getString(SqlColumnName.PASSWORD));
                user.setRole(UserRole.valueOf(resultSet.getString(SqlColumnName.ROLE)));
                user.setStatus(UserStatus.valueOf(resultSet.getString(SqlColumnName.STATUS)));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
        }
        return Optional.of(user);
    }

    public Optional<User> findByUsername(String username) throws DaoException {
        User user = new User();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_BY_USERNAME);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setUserId(resultSet.getLong(SqlColumnName.USER_ID));
                user.setUsername(resultSet.getString(SqlColumnName.USERNAME));
                user.setPassword(resultSet.getString(SqlColumnName.PASSWORD));
                user.setRole(UserRole.valueOf(resultSet.getString(SqlColumnName.ROLE)));
                user.setStatus(UserStatus.valueOf(resultSet.getString(SqlColumnName.STATUS)));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
        }
        return Optional.of(user);
    }

    public Optional<User> authorizeUser(User user) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User authorizeUser = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_BY_USERNAME_AND_PASSWORD);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                authorizeUser = new User();
                authorizeUser.setUserId(resultSet.getLong(SqlColumnName.USER_ID));
                authorizeUser.setUsername(resultSet.getString(SqlColumnName.USERNAME));
                authorizeUser.setPassword(resultSet.getString(SqlColumnName.PASSWORD));
                authorizeUser.setRole(UserRole.valueOf(resultSet.getString(SqlColumnName.ROLE)));
                authorizeUser.setStatus(UserStatus.valueOf(resultSet.getString(SqlColumnName.STATUS)));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
        }
        return Optional.ofNullable(authorizeUser);
    }

    @Override
    public User update(User user) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole().name());
            preparedStatement.setString(4, user.getStatus().name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
        return user;
    }

    @Override
    public boolean delete(User user) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setLong(1, user.getUserId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
        return true;
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> userList = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                User user = new User(resultSet.getLong(SqlColumnName.USER_ID), resultSet.getString(SqlColumnName.USERNAME),
                        resultSet.getString(SqlColumnName.PASSWORD), UserRole.valueOf(resultSet.getString(SqlColumnName.ROLE)),
                        UserStatus.valueOf(resultSet.getString(SqlColumnName.STATUS)));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
        }
        return userList;
    }

    @Override
    public void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public void close(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            logger.error(e);
        }
    }
}