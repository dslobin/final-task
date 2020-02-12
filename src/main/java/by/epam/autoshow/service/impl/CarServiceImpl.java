package by.epam.autoshow.service.impl;

import by.epam.autoshow.dao.DaoException;
import by.epam.autoshow.dao.manager.CarManager;
import by.epam.autoshow.model.Car;
import by.epam.autoshow.service.CarService;
import by.epam.autoshow.service.ServiceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarServiceImpl implements CarService {
    private static volatile CarServiceImpl INSTANCE;
    private static final Logger logger = LogManager.getLogger();
    private CarManager carManager = CarManager.getInstance();

    private CarServiceImpl() {

    }

    public static CarServiceImpl getInstance() {
        CarServiceImpl carService = INSTANCE;
        if (carService == null) {
            synchronized (CarServiceImpl.class) {
                carService = INSTANCE;
                if (carService == null) {
                    INSTANCE = carService = new CarServiceImpl();
                }
            }
        }
        return carService;
    }

    @Override
    public boolean addCar(Car car) throws ServiceException {
        return false;
    }

    @Override
    public Optional<Car> findCarById(long id) throws ServiceException {
        Optional<Car> car = Optional.empty();
        try {
            carManager.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return car;
    }

    @Override
    public List<Car> findAllCars() throws ServiceException {
        List<Car> cars = new ArrayList<>();
        try {
            cars = carManager.findCarList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return cars;
    }
}