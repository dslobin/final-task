package by.epam.autoshow.dao;

import by.epam.autoshow.model.Car;

import java.util.List;
import java.util.Optional;

/**
 * Car data access object interface.
 * Provides CRUD operations with {@link Car} objects.
 *
 * @author Daniil Slobin
 * @see by.epam.autoshow.dao.impl.CarDaoImpl
 */

public interface CarDao {
    /**
     * Save the entity object.
     *
     * @param car object to save
     * @return inserted row id
     * @throws DaoException if a data store access error occurs
     */
    long insert(Car car) throws DaoException;

    /**
     * Find the object by id.
     *
     * @param id entity unique identifier
     * @return optional of entity object
     * @throws DaoException if a data store access error occurs
     */
    Optional<Car> findById(long id) throws DaoException;

    /**
     * Update the entity object.
     *
     * @param car object to update
     * @return updated object
     * @throws DaoException if a data store access error occurs
     */
    Car update(Car car) throws DaoException;

    /**
     * Update car image.
     *
     * @param carId car unique identifier
     * @param imageUrl path to car image
     * @return {@code true} if car image path was updated successfully
     * @throws DaoException if a data store access error occurs
     */
    boolean updateCarImage(long carId, String imageUrl) throws DaoException;

    /**
     * Find car objects in data storage.
     *
     * @return list of objects
     * @throws DaoException if a data store access error occurs
     */
    List<Car> findAll() throws DaoException;

    /**
     * Find car objects with {@code SaleStatus.IN_STOCK} in data storage.
     *
     * @return list of objects
     * @throws DaoException if a data store access error occurs
     */
    List<Car> findCarsForSale() throws DaoException;
}