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

    private static final String FIND_CARS_FOR_SALE =
            "SELECT cars.car_id, cars.model, cars.mileage, cars.fuel_type, cars.body_type," +
                    " cars.volume, cars.transmission, cars.drive_unit, cars.issue_year," +
                    " cars.price, cars.sale_status, colors.color_id, colors.code," +
                    " cars.description, IFNULL(image_url, DEFAULT(image_url)) AS image_url FROM cars" +
                    " LEFT JOIN car_coloring ON cars.car_id = car_coloring.car_id" +
                    " LEFT JOIN colors ON car_coloring.color_id = colors.color_id" +
                    " WHERE cars.sale_status = ?";

    public CarDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public long insert(Car car) throws DaoException {
        long carId = -1;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            editCarTableRow(car, preparedStatement);
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    carId = resultSet.getLong(1);
                }
            } catch (SQLException e) {
                throw new DaoException("Creating car failed, no Id obtained");
            }
        } catch (SQLException e) {
            throw new DaoException("Error adding car", e);
        }
        return carId;
    }

    @Override
    public Optional<Car> findById(long id) throws DaoException {
        Car car = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Color color = new Color(
                            resultSet.getLong(SqlColumnName.COLOR_ID),
                            resultSet.getString(SqlColumnName.CODE)
                    );
                    car = new Car.Builder(
                            resultSet.getString(SqlColumnName.CAR_MODEL),
                            resultSet.getInt(SqlColumnName.MILEAGE),
                            FuelType.valueOf(resultSet.getString(SqlColumnName.FUEL_TYPE)),
                            BodyType.valueOf(resultSet.getString(SqlColumnName.BODY_TYPE)),
                            resultSet.getInt(SqlColumnName.VOLUME),
                            resultSet.getString(SqlColumnName.TRANSMISSION),
                            resultSet.getString(SqlColumnName.DRIVE_UNIT),
                            resultSet.getInt(SqlColumnName.ISSUE_YEAR),
                            resultSet.getBigDecimal(SqlColumnName.PRICE),
                            SaleStatus.valueOf(resultSet.getString(SqlColumnName.SALE_STATUS)))
                            .setCarId(resultSet.getLong(SqlColumnName.CAR_ID))
                            .setColor(color)
                            .setDescription(resultSet.getString(SqlColumnName.DESCRIPTION))
                            .setImageUrl(resultSet.getString(SqlColumnName.IMAGE_URL))
                            .build();
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error finding car by id", e);
        }
        return Optional.ofNullable(car);
    }

    @Override
    public Car update(Car car) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            editCarTableRow(car, preparedStatement);
            preparedStatement.setLong(12, car.getCarId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error updating car", e);
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
    public boolean updateCarImage(long carId, String imageUrl) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CAR_IMAGE)) {
            preparedStatement.setString(1, imageUrl);
            preparedStatement.setLong(2, carId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error updating car image", e);
        }
        return true;
    }

    @Override
    public List<Car> findAll() throws DaoException {
        List<Car> carList = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
            resultSetToList(carList, resultSet);
        } catch (SQLException e) {
            throw new DaoException("Error finding cars", e);
        }
        return carList;
    }

    @Override
    public List<Car> findCarsForSale() throws DaoException {
        List<Car> carList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_CARS_FOR_SALE)) {
            preparedStatement.setString(1, SaleStatus.IN_STOCK.name());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSetToList(carList, resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Error finding cars for sale", e);
        }
        return carList;
    }

    private void resultSetToList(List<Car> carList, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Color color = new Color(
                    resultSet.getLong(SqlColumnName.COLOR_ID),
                    resultSet.getString(SqlColumnName.CODE)
            );
            Car car = new Car.Builder(
                    resultSet.getString(SqlColumnName.CAR_MODEL),
                    resultSet.getInt(SqlColumnName.MILEAGE),
                    FuelType.valueOf(resultSet.getString(SqlColumnName.FUEL_TYPE)),
                    BodyType.valueOf(resultSet.getString(SqlColumnName.BODY_TYPE)),
                    resultSet.getInt(SqlColumnName.VOLUME),
                    resultSet.getString(SqlColumnName.TRANSMISSION),
                    resultSet.getString(SqlColumnName.DRIVE_UNIT),
                    resultSet.getInt(SqlColumnName.ISSUE_YEAR),
                    resultSet.getBigDecimal(SqlColumnName.PRICE),
                    SaleStatus.valueOf(resultSet.getString(SqlColumnName.SALE_STATUS)))
                    .setCarId(resultSet.getLong(SqlColumnName.CAR_ID))
                    .setColor(color)
                    .setDescription(resultSet.getString(SqlColumnName.DESCRIPTION))
                    .setImageUrl(resultSet.getString(SqlColumnName.IMAGE_URL))
                    .build();
            carList.add(car);
        }
    }
}