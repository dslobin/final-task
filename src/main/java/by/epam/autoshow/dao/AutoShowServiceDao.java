package by.epam.autoshow.dao;

import by.epam.autoshow.model.AutoShowService;

import java.sql.ResultSet;
import java.sql.Statement;

import java.util.List;
import java.util.Optional;

/**
 * Auto Show Service data access object interface.
 * Provides CRUD operations with {@link AutoShowService} objects.
 *
 * @author Daniil Slobin
 */

public interface AutoShowServiceDao {
    /**
     * Save the entity object.
     *
     * @param autoShowService object to save
     * @return {@code true} if object has been added to the data storage
     * @throws DaoException if a data store access error occurs
     */
    boolean insert(AutoShowService autoShowService) throws DaoException;

    /**
     * Find the object by id.
     *
     * @param id entity unique identifier
     * @return optional of entity object
     * @throws DaoException if a data store access error occurs
     */
    Optional<AutoShowService> findById(long id) throws DaoException;

    /**
     * Update the entity object.
     *
     * @param autoShowService object to update
     * @return updated object
     * @throws DaoException if a data store access error occurs
     */
    AutoShowService update(AutoShowService autoShowService) throws DaoException;

    /**
     * Delete the object from data storage.
     *
     * @param autoShowService object to delete
     * @return {@code true} if object has been deleted from the data storage
     * @throws DaoException if a data store access error occurs
     */
    boolean delete(AutoShowService autoShowService) throws DaoException;

    /**
     * Find entity objects in data storage.
     *
     * @return list of objects
     * @throws DaoException if a data store access error occurs
     */
    List<AutoShowService> findAll() throws DaoException;

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