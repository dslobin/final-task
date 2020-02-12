package by.epam.autoshow.service.impl;

import by.epam.autoshow.dao.DaoException;
import by.epam.autoshow.dao.impl.CarDaoImpl;
import by.epam.autoshow.db.ConnectionPool;
import by.epam.autoshow.model.Car;
import by.epam.autoshow.service.CarService;
import by.epam.autoshow.service.ServiceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarServiceImpl implements CarService {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public boolean addCar(Car car) throws ServiceException {
        return false;
    }

    @Override
    public Optional<Car> findCarById(long id) throws ServiceException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.getConnection();
        CarDaoImpl carDao = new CarDaoImpl(connection);
        Optional<Car> car = Optional.empty();
        try {
            car = carDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return car;
    }

    @Override
    public List<Car> findAllCars() throws ServiceException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.getConnection();
        CarDaoImpl carDao = new CarDaoImpl(connection);
        List<Car> carList = new ArrayList<>();
        try {
            carList = carDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return carList;
    }

}
