package by.epam.autoshow.service;

import by.epam.autoshow.model.Customer;
import by.epam.autoshow.model.User;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Optional<Customer> findCustomerByLogin(String login) throws ServiceException;

    List<Customer> findAllCustomers() throws ServiceException;

    Optional<Customer> findCustomerById(long id) throws ServiceException;

    boolean registerCustomer(User user, Customer customer) throws ServiceException;

    boolean updateCustomer(User user, Customer customer) throws ServiceException;
}
