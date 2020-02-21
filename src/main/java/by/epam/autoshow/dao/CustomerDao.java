package by.epam.autoshow.dao;

import by.epam.autoshow.model.Customer;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Customer data access object interface.
 * Provides CRUD operations with {@link Customer} objects.
 *
 * @author Daniil Slobin
 */

public interface CustomerDao {
    /**
     * Save the entity object.
     *
     * @param customer object to save
     * @return {@code true} if object has been added to the data storage
     * @throws DaoException if a data store access error occurs
     */
    boolean insert(Customer customer) throws DaoException;

    /**
     * Find the object by id.
     *
     * @param id entity unique identifier
     * @return optional of entity object
     * @throws DaoException if a data store access error occurs
     */
    Optional<Customer> findById(long id) throws DaoException;

    /**
     * Update the entity object.
     *
     * @param customer object to update
     * @return updated object
     * @throws DaoException if a data store access error occurs
     */
    Customer update(Customer customer) throws DaoException;

    /**
     * Find the customer usernames
     *
     * @return map which contains the username as a key and customer data as a value
     * @throws DaoException if a data store access error occurs
     */
    Map<String, Customer> findCustomerUserNames() throws DaoException;

    /**
     * Find entity objects in data storage.
     *
     * @return list of objects
     * @throws DaoException if a data store access error occurss
     */
    List<Customer> findAll() throws DaoException;
}