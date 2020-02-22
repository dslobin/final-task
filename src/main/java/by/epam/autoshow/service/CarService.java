package by.epam.autoshow.service;

import by.epam.autoshow.model.Car;
import by.epam.autoshow.model.Color;
import by.epam.autoshow.validation.ValidatorException;

import java.util.List;
import java.util.Optional;

/**
 * Coordinates tasks and delegates work to collaborations of {@link Car} objects in the
 * dao manager layer
 *
 * @author Daniil Slobin
 * @see by.epam.autoshow.dao.manager.CarManager
 * @see by.epam.autoshow.service.impl.CarServiceImpl
 */

public interface CarService {
    /**
     * Finds cars
     *
     * @return car list
     * @throws ServiceException if dao manager errors occurs
     */
    List<Car> findAllCars() throws ServiceException;

    /**
     * Finds car by id
     *
     * @param id car unique identifier
     * @return {@code Optional} of car
     * @throws ServiceException if dao manager errors occurs
     */
    Optional<Car> findCarById(long id) throws ServiceException;

    boolean updateCarImage(Car car) throws ServiceException;

    /**
     *
     * @param car data
     * @param colorCode car color
     * @return {@code true} if car was added successfully
     * @throws ServiceException if dao manager errors occurs
     */
    boolean addCar(Car car, String colorCode) throws ServiceException, ValidatorException;

    /**
     *
     * @param car data
     * @param color car color
     * @return {@code true} if car was updated successfully
     * @throws ServiceException if dao manager errors occurs
     */
    boolean updateCar(Car car, String color) throws ServiceException, ValidatorException;

    /**
     * Finds car colors
     *
     * @return car colors list
     * @throws ServiceException if dao manager errors occurs
     */
    List<Color> findAllColors() throws ServiceException;
}