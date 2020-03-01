package by.epam.autoshow.dao;

import by.epam.autoshow.model.User;

import java.util.List;
import java.util.Optional;

/**
 * User data access object interface.
 * Provides CRUD operations with {@link User} objects.
 *
 * @author Daniil Slobin
 * @see by.epam.autoshow.dao.impl.UserDaoImpl
 */

public interface UserDao {
    /**
     * Save the entity object.
     *
     * @param user object to save
     * @return {@code true} if object has been added to the data storage
     * @throws DaoException if a data store access error occurs
     */
    boolean insert(User user) throws DaoException;

    /**
     * Find the object by id.
     *
     * @param id entity unique identifier
     * @return optional of entity object
     * @throws DaoException if a data store access error occurs
     */
    Optional<User> findById(long id) throws DaoException;

    /**
     * Update the entity object.
     *
     * @param entity object to update
     * @return updated object
     * @throws DaoException if a data store access error occurs
     */
    User update(User entity) throws DaoException;

    /**
     * Find entity objects in data storage.
     *
     * @return list of objects
     * @throws DaoException if a data store access error occurs
     */
    List<User> findAll() throws DaoException;
}