package by.epam.autoshow.dao;

import by.epam.autoshow.model.Order;
import by.epam.autoshow.model.Customer;

import java.util.List;
import java.util.Optional;

/**
 * Oder data access object interface.
 * Provides CRUD operations with {@link Order} objects.
 *
 * @author Daniil Slobin
 * @see by.epam.autoshow.dao.impl.OrderDaoImpl
 */

public interface OrderDao {
    /**
     * Save the order object.
     *
     * @param order object to save
     * @return {@code true} if object has been added to the data storage
     * @throws DaoException if a data store access error occurs
     */
    boolean insert(Order order) throws DaoException;

    /**
     * Delete the order object.
     *
     * @param order object to delete
     * @return {@code true} if object has been deleted from the data storage
     * @throws DaoException if a data store access error occurs
     */
    boolean delete(Order order) throws DaoException;

    /**
     * Find the object by id.
     *
     * @param id entity unique identifier
     * @return optional of entity object
     * @throws DaoException if a data store access error occurs
     */
    Optional<Order> findById(long id) throws DaoException;

    /**
     * Find the object by customer id.
     *
     * @param id customer {@link Customer} entity unique identifier
     * @return optional of entity object
     * @throws DaoException if a data store access error occurs
     */
    List<Order> findByCustomerId(long id) throws DaoException;

    /**
     * Update the entity object.
     *
     * @param order object to update
     * @return updated object
     * @throws DaoException if a data store access error occurs
     */
    Order update(Order order) throws DaoException;

    /**
     * Update the order status.
     *
     * @param order object to update
     * @return {@code true} if order status was updated successfully
     * @throws DaoException if a data store access error occurs
     */
    boolean updateOrderStatus(Order order) throws DaoException;

    /**
     * Find entity objects in data storage.
     *
     * @return list of objects
     * @throws DaoException if a data store access error occurs
     */
    List<Order> findAll() throws DaoException;
}