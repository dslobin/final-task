package by.epam.autoshow.dao.manager;

import by.epam.autoshow.dao.ColorDao;
import by.epam.autoshow.dao.DaoException;
import by.epam.autoshow.dao.impl.CarDaoImpl;
import by.epam.autoshow.dao.impl.ColorDaoImpl;
import by.epam.autoshow.model.Car;
import by.epam.autoshow.model.Color;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarManager extends DaoManager {
    private static volatile CarManager INSTANCE;
    private static final Logger logger = LogManager.getLogger();

    private CarManager() {

    }

    public static CarManager getInstance() {
        CarManager carManager = INSTANCE;
        if (carManager == null) {
            synchronized (CarManager.class) {
                carManager = INSTANCE;
                if (carManager == null) {
                    INSTANCE = carManager = new CarManager();
                }
            }
        }
        return carManager;
    }

    public boolean updateCar(Car car, String colorCode) throws DaoException, SQLException {
        Connection connection = getTXNConnection();
        try {
            CarDaoImpl carDao = new CarDaoImpl(connection);
            ColorDaoImpl colorDao = new ColorDaoImpl(connection);
            carDao.update(car);
            Optional<Color> color = colorDao.findByCode(colorCode);
            colorDao.updateCarColor(car.getCarId(), color.get().getColorId());
            connection.commit();
        } catch (DaoException e) {
            connection.rollback();
            throw new DaoException(e);
        } finally {
            close(connection);
        }
        return true;
    }

    public boolean updateCarImage(Car car) throws DaoException {
        Connection connection = getConnection();
        try {
            CarDaoImpl carDao = new CarDaoImpl(connection);
            carDao.updateCarImage(car);
        } catch (DaoException e) {
            throw new DaoException(e);
        } finally {
            close(connection);
        }
        return true;
    }

    public boolean addCar(Car car, String colorCode) throws DaoException, SQLException {
        Connection connection = getTXNConnection();
        try {
            CarDaoImpl carDao = new CarDaoImpl(connection);
            ColorDaoImpl colorDao = new ColorDaoImpl(connection);
            carDao.insert(car);
            Optional<Color> color = colorDao.findByCode(colorCode);
            long carId = carDao.insert(car);
            colorDao.insertCarColor(carId, color.get().getColorId());
            connection.commit();
        } catch (DaoException e) {
            connection.rollback();
            throw new DaoException(e);
        } finally {
            close(connection);
        }
        return true;
    }

    public Optional<Car> findById(long id) throws DaoException {
        Connection connection = getConnection();
        Optional<Car> car = Optional.empty();
        try {
            CarDaoImpl carDao = new CarDaoImpl(connection);
            car = carDao.findById(id);
        } catch (DaoException e) {
            throw new DaoException(e);
        } finally {
            close(connection);
        }
        return car;
    }

    public List<Car> findCarList() throws DaoException {
        Connection connection = getConnection();
        List<Car> carList = new ArrayList<>();
        try {
            CarDaoImpl carDao = new CarDaoImpl(connection);
            carList = carDao.findAll();
        } catch (DaoException e) {
            throw new DaoException(e);
        } finally {
            close(connection);
        }
        return carList;
    }

    public List<Color> findAllCarColors() throws DaoException {
        Connection connection = getConnection();
        List<Color> colors = new ArrayList<>();
        try {
            ColorDao colorDao = new ColorDaoImpl(connection);
            colors = colorDao.findAll();
        } catch (DaoException e) {
            throw new DaoException(e);
        } finally {
            close(connection);
        }
        return colors;
    }
}