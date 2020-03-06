package by.epam.autoshow.service.impl;

import by.epam.autoshow.dao.manager.CarManager;
import by.epam.autoshow.dao.manager.ManagerException;
import by.epam.autoshow.model.Car;
import by.epam.autoshow.model.Color;
import by.epam.autoshow.service.CarService;
import by.epam.autoshow.service.ServiceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarServiceImpl implements CarService {
    private static volatile CarServiceImpl INSTANCE;
    private CarManager carManager;
    private static final Logger logger = LogManager.getLogger();

    private CarServiceImpl() {
        carManager = CarManager.getInstance();
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
    public boolean updateCar(Car car, String colorCode) throws ServiceException {
        try {
            carManager.updateCar(car, colorCode);
        } catch (ManagerException | SQLException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public boolean updateCarImage(long carId, String imageUrl) throws ServiceException {
        try {
            carManager.updateCarImage(carId, imageUrl);
        } catch (ManagerException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public boolean addCar(Car car, String colorCode) throws ServiceException {
        try {
            carManager.addCar(car, colorCode);
        } catch (ManagerException | SQLException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public Optional<Car> findCarById(long id) throws ServiceException {
        Optional<Car> car = Optional.empty();
        try {
            car = carManager.findById(id);
        } catch (ManagerException e) {
            throw new ServiceException(e);
        }
        return car;
    }

    @Override
    public List<Car> findCarsForSale() throws ServiceException {
        List<Car> cars = new ArrayList<>();
        try {
            cars = carManager.findCarsForSale();
        } catch (ManagerException e) {
            throw new ServiceException(e);
        }
        return cars;
    }

    @Override
    public List<Car> findAllCars() throws ServiceException {
        List<Car> cars = new ArrayList<>();
        try {
            cars = carManager.findCarList();
        } catch (ManagerException e) {
            throw new ServiceException(e);
        }
        return cars;
    }

    @Override
    public List<Color> findAllColors() throws ServiceException {
        List<Color> colors = new ArrayList<>();
        try {
            colors = carManager.findAllCarColors();
        } catch (ManagerException e) {
            throw new ServiceException(e);
        }
        return colors;
    }
}