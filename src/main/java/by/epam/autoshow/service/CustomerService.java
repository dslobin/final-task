package by.epam.autoshow.service;

import by.epam.autoshow.model.Customer;
import by.epam.autoshow.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Coordinates tasks and delegates work to collaborations of {@link Customer} objects in the
 * dao manager layer
 *
 * @author Daniil Slobin
 * @see by.epam.autoshow.dao.manager.CustomerManger
 * @see by.epam.autoshow.service.impl.CustomerServiceImpl
 */

public interface CustomerService {
    /**
     * Finds customer by login
     *
     * @param login customer unique identifier
     * @return {@code Optional} of customer
     * @throws ServiceException if dao manager errors occurs
     */
    Optional<Customer> findCustomerByLogin(String login) throws ServiceException;

    /**
     * Finds customers
     *
     * @return list of customers
     * @throws ServiceException if dao manager errors occurs
     */
    List<Customer> findAllCustomers() throws ServiceException;

    /**
     * Finds customer bu id
     *
     * @param id customer unique identifier
     * @return {@code Optional} of customer
     * @throws ServiceException if dao manager errors occurs
     */
    Optional<Customer> findCustomerById(long id) throws ServiceException;

    /**
     * Register customer
     *
     * @param user account data
     * @param customer personal details
     * @return {@code true} if customer registration was successfully
     * @throws ServiceException if dao manager errors occurs
     */
    boolean registerCustomer(User user, Customer customer) throws ServiceException;

    /**
     * Update customer data
     *
     * @param user account data
     * @param customer personal details
     * @return {@code true} if customer data was updated successfully
     * @throws ServiceException if dao manager errors occurs
     */
    boolean updateCustomer(User user, Customer customer) throws ServiceException;

    /**
     * Finds customer user names
     *
     * @return {@code Map} with username as a key and customer data as a value
     * @throws ServiceException if dao manager errors occurs
     */
    Map<String, Customer> findCustomerUserNames() throws ServiceException;
}
