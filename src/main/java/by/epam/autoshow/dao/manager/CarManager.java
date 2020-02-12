package by.epam.autoshow.dao.manager;

import by.epam.autoshow.dao.DaoException;
import by.epam.autoshow.dao.impl.CarDaoImpl;
import by.epam.autoshow.model.Car;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

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

    public boolean addCar(Car car) throws DaoException {
        return false;
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
}