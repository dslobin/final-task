package by.epam.autoshow.dao.impl;

import by.epam.autoshow.dao.ColorDao;
import by.epam.autoshow.dao.DaoException;
import by.epam.autoshow.dao.SqlColumnName;
import by.epam.autoshow.model.Color;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ColorDaoImpl implements ColorDao {
    private Connection connection;

    private static final String FIND_ALL_COLORS =
            "SELECT color_id, code FROM colors";

    private static final String FIND_COLOR_BY_ID =
            "SELECT color_id, code FROM colors WHERE color_id = ?";

    private static final String FIND_COLOR_BY_CODE =
            "SELECT color_id, code FROM colors WHERE code = ?";

    private static final String UPDATE_CAR_COLOR =
            "UPDATE car_coloring SET color_id = ? WHERE car_id = ?";

    private static final String INSERT_CAR_COLOR =
            "INSERT INTO car_coloring(car_id, color_id) VALUES (?, ?)";

    public ColorDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public void updateCarColor(long carId, long colorId) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CAR_COLOR)) {
            preparedStatement.setLong(1, colorId);
            preparedStatement.setLong(2, carId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void insertCarColor(long carId, long colorId) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT_CAR_COLOR);
            preparedStatement.setLong(1, colorId);
            preparedStatement.setLong(2, carId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Color> findById(long id) throws DaoException {
        Color color = new Color();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_COLOR_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    color.setColorId(resultSet.getLong(SqlColumnName.USER_ID));
                    color.setCode(resultSet.getString(SqlColumnName.USERNAME));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.of(color);
    }

    public Optional<Color> findByCode(String code) throws DaoException {
        Color color = new Color();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_COLOR_BY_CODE)) {
            preparedStatement.setString(1, code);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    color.setColorId(resultSet.getLong(SqlColumnName.COLOR_ID));
                    color.setCode(resultSet.getString(SqlColumnName.CODE));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.of(color);
    }

    @Override
    public List<Color> findAll() throws DaoException {
        List<Color> colorList = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_COLORS)) {
            while (resultSet.next()) {
                Color color = new Color();
                color.setColorId(resultSet.getLong(SqlColumnName.COLOR_ID));
                color.setCode(resultSet.getString(SqlColumnName.CODE));
                colorList.add(color);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return colorList;
    }
}