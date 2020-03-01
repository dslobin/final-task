package by.epam.autoshow.dao;

import by.epam.autoshow.model.Color;

import java.util.List;
import java.util.Optional;

/**
 * Color data access object interface.
 * Provides CRUD operations with {@link Color} objects.
 *
 * @author Daniil Slobin
 */

public interface ColorDao {

    /**
     * Update the car color.
     *
     * @param carId   car unique identifier
     * @param colorId color unique identifier
     * @return inserted row id
     * @throws DaoException if a data store access error occurs
     */
    void updateCarColor(long carId, long colorId) throws DaoException;

    /**
     * Save the car color.
     *
     * @param carId   car unique identifier
     * @param colorId color unique identifier
     * @return inserted row id
     * @throws DaoException if a data store access error occurs
     */
    void insertCarColor(long carId, long colorId) throws DaoException;

    /**
     * Find the object by id.
     *
     * @param id entity unique identifier
     * @return optional of entity object
     * @throws DaoException if a data store access error occurs
     */
    Optional<Color> findById(long id) throws DaoException;

    /**
     * Find the object by color code.
     *
     * @param color entity color
     * @return optional of entity object
     * @throws DaoException if a data store access error occurs
     */
    Optional<Color> findByCode(String color) throws DaoException;

    /**
     * Find entity objects in data storage.
     *
     * @return list of objects
     * @throws DaoException if a data store access error occurs
     */
    List<Color> findAll() throws DaoException;
}