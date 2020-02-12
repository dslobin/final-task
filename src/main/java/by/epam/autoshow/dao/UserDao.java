package by.epam.autoshow.dao;

import by.epam.autoshow.model.User;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    /**
     * Save the entity object.
     *
     * @param entity object to save
     * @return {@code true} if object has been added to the data storage
     * @throws DaoException if a database access error occurs
     */
    boolean insert(User entity) throws DaoException;

    /**
     * Find the object by id.
     *
     * @param id entity unique identifier
     * @return optional of entity object
     * @throws DaoException if a database access error occurs
     */
    Optional<User> findById(long id) throws DaoException;

    /**
     * Update the entity object.
     *
     * @param entity object to save
     * @return updated object
     * @throws DaoException if a database access error occurs
     */
    User update(User entity) throws DaoException;

    /**
     * Delete the object from data storage.
     *
     * @param entity
     * @return {@code true} if object has been deleted from the data storage
     * @throws DaoException if a database access error occurs
     */
    boolean delete(User entity) throws DaoException;

    /**
     * Find entity objects in data storage.
     *
     * @return list of objects
     * @throws DaoException if a database access error occurs
     */
    List<User> findAll() throws DaoException;

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
