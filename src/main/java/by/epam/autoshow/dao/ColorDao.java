package by.epam.autoshow.dao;

import by.epam.autoshow.model.Color;

import java.sql.ResultSet;
import java.sql.Statement;

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

    /**
     * Close statement.
     *
     * @see Statement
     */
    void close(Statement statement);

    /**
     * Close result set.
     *
     * @see ResultSet
     */
    void close(ResultSet resultSet);
}