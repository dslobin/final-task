package by.epam.autoshow.dao.impl;

import by.epam.autoshow.dao.CarDao;
import by.epam.autoshow.dao.DaoException;
import by.epam.autoshow.dao.SqlColumnName;
import by.epam.autoshow.model.Car;
import by.epam.autoshow.model.FuelType;
import by.epam.autoshow.model.SaleStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("Duplicates")
public class CarDaoImpl implements CarDao {
    private Connection connection;

    private static final String INSERT =
            "INSERT INTO cars (model, mileage, fuel_type, body_type_id, volume," +
                    " transmission, drive_unit, issue_year, price, sale_status, description, image_url)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE =
            "UPDATE cars SET model = ?, mileage = ?, fuel_type = ?, body_type_id = ?," +
                    " volume = ?, transmission = ?, drive_unit = ?, issue_year = ?, price = ?, sale_status = ?," +
                    " description = ?, image_url = ? WHERE car_id = ?";

    private static final String FIND_ALL =
            "SELECT cars.car_id, cars.model, cars.mileage, cars.fuel_type, body_types.body_type_id," +
                    " body_types.title, cars.volume, cars.transmission, cars.drive_unit, cars.issue_year," +
                    " cars.price, cars.sale_status, colors.color_id, colors.code," +
                    " cars.description, cars.image_url FROM cars" +
                    " LEFT JOIN car_coloring ON cars.car_id = car_coloring.car_id" +
                    " LEFT JOIN colors ON car_coloring.color_id = colors.color_id" +
                    " INNER JOIN body_types ON cars.body_type_id = body_types.body_type_id";

    private static final String FIND_BY_ID =
            "SELECT cars.car_id, cars.model, cars.mileage, cars.fuel_type, body_types.body_type_id AS body_id," +
                    " body_types.title AS body_title, cars.volume, cars.transmission, cars.drive_unit, cars.issue_year," +
                    " cars.price, cars.sale_status, colors.color_id AS color_id, colors.code AS color_code," +
                    " cars.description, cars.image_url FROM cars" +
                    " LEFT JOIN car_coloring ON cars.car_id = car_coloring.car_id" +
                    " LEFT JOIN colors ON car_coloring.color_id = colors.color_id" +
                    " INNER JOIN body_types ON cars.body_type_id = body_types.body_type_id" +
                    " WHERE car_id = ?";

    private static final Logger logger = LogManager.getLogger();

    public CarDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean insert(Car car) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, car.getModel());
            preparedStatement.setInt(2, car.getMileage());
            preparedStatement.setString(3, car.getFuelType().name());
            preparedStatement.setLong(4, car.getBodyType().getBodyTypeId());
            preparedStatement.setInt(5, car.getVolume());
            preparedStatement.setString(6, car.getTransmission());
            preparedStatement.setString(7, car.getDriveUnit());
            preparedStatement.setInt(8, car.getIssueYear());
            preparedStatement.setBigDecimal(9, car.getPrice());
            preparedStatement.setString(10, car.getStatus().name());
            preparedStatement.setString(11, car.getDescription());
            preparedStatement.setString(12, car.getImageUrl());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
        return true;
    }

    @Override
    public Optional<Car> findById(long id) throws DaoException {
        Car car = new Car();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                car.setCarId(resultSet.getLong(SqlColumnName.CAR_ID));
                car.setModel(resultSet.getString(SqlColumnName.CAR_MODEL));
                car.setMileage(resultSet.getInt(SqlColumnName.MILEAGE));
                car.setFuelType(FuelType.valueOf(resultSet.getString(SqlColumnName.FUEL_TYPE)));
                car.getBodyType().setBodyTypeId(resultSet.getLong(SqlColumnName.BODY_TYPE_ID));
                car.getBodyType().setTitle(resultSet.getString(SqlColumnName.TITLE));
                car.setVolume(resultSet.getInt(SqlColumnName.VOLUME));
                car.setTransmission(resultSet.getString(SqlColumnName.TRANSMISSION));
                car.setDriveUnit(resultSet.getString(SqlColumnName.DRIVE_UNIT));
                car.setIssueYear(resultSet.getInt(SqlColumnName.ISSUE_YEAR));
                car.setPrice(resultSet.getBigDecimal(SqlColumnName.PRICE));
                car.setStatus(SaleStatus.valueOf(resultSet.getString(SqlColumnName.SALE_STATUS)));
                car.getColor().setColorId(resultSet.getLong(SqlColumnName.COLOR_ID));
                car.getColor().setCode(resultSet.getString(SqlColumnName.CODE));
                car.setDescription(resultSet.getString(SqlColumnName.DESCRIPTION));
                car.setImageUrl(resultSet.getString(SqlColumnName.IMAGE_URL));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
        }
        return Optional.of(car);
    }

    @Override
    public Car update(Car car) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, car.getModel());
            preparedStatement.setInt(2, car.getMileage());
            preparedStatement.setString(3, car.getFuelType().name());
            preparedStatement.setLong(4, car.getBodyType().getBodyTypeId());
            preparedStatement.setInt(5, car.getVolume());
            preparedStatement.setString(6, car.getTransmission());
            preparedStatement.setString(7, car.getDriveUnit());
            preparedStatement.setInt(8, car.getIssueYear());
            preparedStatement.setBigDecimal(9, car.getPrice());
            preparedStatement.setString(10, car.getStatus().name());
            preparedStatement.setString(11, car.getDescription());
            preparedStatement.setString(12, car.getImageUrl());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
        return car;
    }

    @Override
    public boolean delete(Car car) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Car> findAll() throws DaoException {
        List<Car> carList = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                Car car = new Car();
                car.setCarId(resultSet.getLong(SqlColumnName.CAR_ID));
                car.setModel(resultSet.getString(SqlColumnName.CAR_MODEL));
                car.setMileage(resultSet.getInt(SqlColumnName.MILEAGE));
                car.setFuelType(FuelType.valueOf(resultSet.getString(SqlColumnName.FUEL_TYPE)));
                car.getBodyType().setBodyTypeId(resultSet.getLong(SqlColumnName.BODY_TYPE_ID));
                car.getBodyType().setTitle(resultSet.getString(SqlColumnName.TITLE));
                car.setVolume(resultSet.getInt(SqlColumnName.VOLUME));
                car.setTransmission(resultSet.getString(SqlColumnName.TRANSMISSION));
                car.setDriveUnit(resultSet.getString(SqlColumnName.DRIVE_UNIT));
                car.setIssueYear(resultSet.getInt(SqlColumnName.ISSUE_YEAR));
                car.setPrice(resultSet.getBigDecimal(SqlColumnName.PRICE));
                car.setStatus(SaleStatus.valueOf(resultSet.getString(SqlColumnName.SALE_STATUS)));
                car.getColor().setColorId(resultSet.getLong(SqlColumnName.COLOR_ID));
                car.getColor().setCode(resultSet.getString(SqlColumnName.CODE));
                car.setDescription(resultSet.getString(SqlColumnName.DESCRIPTION));
                car.setImageUrl(resultSet.getString(SqlColumnName.IMAGE_URL));
                carList.add(car);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
        }
        return carList;
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