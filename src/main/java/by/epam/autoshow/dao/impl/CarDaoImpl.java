package by.epam.autoshow.dao.impl;

import by.epam.autoshow.dao.CarDao;
import by.epam.autoshow.dao.DaoException;
import by.epam.autoshow.dao.SqlColumnName;
import by.epam.autoshow.model.*;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDaoImpl implements CarDao {
    private Connection connection;

    private static final String INSERT =
            "INSERT INTO cars (model, mileage, fuel_type, body_type, volume," +
                    " transmission, drive_unit, issue_year, price, sale_status, description)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE =
            "UPDATE cars SET model = ?, mileage = ?, fuel_type = ?, body_type = ?," +
                    " volume = ?, transmission = ?, drive_unit = ?, issue_year = ?, price = ?, sale_status = ?," +
                    " description = ? WHERE car_id = ?";

    private static final String UPDATE_CAR_IMAGE =
            "UPDATE cars SET image_url = ? WHERE car_id = ?";

    private static final String FIND_ALL =
            "SELECT cars.car_id, cars.model, cars.mileage, cars.fuel_type, cars.body_type," +
                    " cars.volume, cars.transmission, cars.drive_unit, cars.issue_year," +
                    " cars.price, cars.sale_status, colors.color_id, colors.code," +
                    " cars.description, IFNULL(image_url, DEFAULT(image_url)) AS image_url FROM cars" +
                    " LEFT JOIN car_coloring ON cars.car_id = car_coloring.car_id" +
                    " LEFT JOIN colors ON car_coloring.color_id = colors.color_id";

    private static final String FIND_BY_ID =
            "SELECT cars.car_id, cars.model, cars.mileage, cars.fuel_type, cars.body_type," +
                    " cars.volume, cars.transmission, cars.drive_unit, cars.issue_year," +
                    " cars.price, cars.sale_status, colors.color_id, colors.code," +
                    " cars.description, IFNULL(image_url, DEFAULT(image_url)) AS image_url FROM cars" +
                    " LEFT JOIN car_coloring ON cars.car_id = car_coloring.car_id" +
                    " LEFT JOIN colors ON car_coloring.color_id = colors.color_id" +
                    " WHERE cars.car_id = ?";

    public CarDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public long insert(Car car) throws DaoException {
        long carId = -1;
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            editCarTableRow(car, preparedStatement);
            preparedStatement.executeUpdate();
            carId = getInsertedRecordId(preparedStatement);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return carId;
    }

    private long getInsertedRecordId(Statement statement) throws SQLException {
        ResultSet resultSet = statement.getGeneratedKeys();
        long id = -1;
        if (resultSet.next()) {
            id = resultSet.getLong(SqlColumnName.CAR_ID);
        }
        return id;
    }

    @Override
    public Optional<Car> findById(long id) throws DaoException {
        Car car = new Car();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    car.setCarId(resultSet.getLong(SqlColumnName.CAR_ID));
                    car.setModel(resultSet.getString(SqlColumnName.CAR_MODEL));
                    car.setMileage(resultSet.getInt(SqlColumnName.MILEAGE));
                    car.setFuelType(FuelType.valueOf(resultSet.getString(SqlColumnName.FUEL_TYPE)));
                    car.setBodyType(BodyType.valueOf(resultSet.getString(SqlColumnName.BODY_TYPE)));
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
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.of(car);
    }

    @Override
    public Car update(Car car) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            editCarTableRow(car, preparedStatement);
            preparedStatement.setLong(12, car.getCarId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return car;
    }

    private void editCarTableRow(Car car, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, car.getModel());
        preparedStatement.setInt(2, car.getMileage());
        preparedStatement.setString(3, car.getFuelType().name());
        preparedStatement.setString(4, car.getBodyType().name());
        preparedStatement.setInt(5, car.getVolume());
        preparedStatement.setString(6, car.getTransmission());
        preparedStatement.setString(7, car.getDriveUnit());
        preparedStatement.setInt(8, car.getIssueYear());
        preparedStatement.setBigDecimal(9, car.getPrice());
        preparedStatement.setString(10, car.getStatus().name());
        preparedStatement.setString(11, car.getDescription());
    }

    @Override
    public Car updateCarImage(Car car) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CAR_IMAGE)) {
            preparedStatement.setString(1, car.getImageUrl());
            preparedStatement.setLong(2, car.getCarId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return car;
    }

    @Override
    public List<Car> findAll() throws DaoException {
        List<Car> carList = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
            while (resultSet.next()) {
                Color color = new Color(
                        resultSet.getLong(SqlColumnName.COLOR_ID),
                        resultSet.getString(SqlColumnName.CODE)
                );
                Car car = new Car(
                        resultSet.getLong(SqlColumnName.CAR_ID),
                        resultSet.getString(SqlColumnName.CAR_MODEL),
                        resultSet.getInt(SqlColumnName.MILEAGE),
                        FuelType.valueOf(resultSet.getString(SqlColumnName.FUEL_TYPE)),
                        BodyType.valueOf(resultSet.getString(SqlColumnName.BODY_TYPE)),
                        resultSet.getInt(SqlColumnName.VOLUME),
                        resultSet.getString(SqlColumnName.TRANSMISSION),
                        resultSet.getString(SqlColumnName.DRIVE_UNIT),
                        color,
                        resultSet.getInt(SqlColumnName.ISSUE_YEAR),
                        resultSet.getBigDecimal(SqlColumnName.PRICE),
                        SaleStatus.valueOf(resultSet.getString(SqlColumnName.SALE_STATUS)),
                        resultSet.getString(SqlColumnName.DESCRIPTION),
                        resultSet.getString(SqlColumnName.IMAGE_URL)
                );
                carList.add(car);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return carList;
    }
}