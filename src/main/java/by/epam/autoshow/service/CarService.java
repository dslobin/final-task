package by.epam.autoshow.service;

import by.epam.autoshow.model.Car;
import by.epam.autoshow.model.Color;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<Car> findAllCars() throws ServiceException;

    Optional<Car> findCarById(long id) throws ServiceException;

    boolean addCar(Car car, String colorCode) throws ServiceException;

    boolean updateCar(Car car, String color) throws ServiceException;

    List<Color> findAllColors() throws ServiceException;
}