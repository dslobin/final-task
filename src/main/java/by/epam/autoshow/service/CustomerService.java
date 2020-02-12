package by.epam.autoshow.service;

import by.epam.autoshow.model.Customer;
import by.epam.autoshow.model.User;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> findAllCustomers() throws ServiceException;
    Optional<Customer> findCustomerById(long id) throws ServiceException;
    boolean registerCustomer(User user, Customer customer) throws ServiceException;
}
