package by.epam.autoshow.service;

import by.epam.autoshow.model.car.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<Car> findAllCars() throws ServiceException;
    Optional<Car> findCarById(long id) throws ServiceException;
    boolean addCar(Car car) throws ServiceException;
}