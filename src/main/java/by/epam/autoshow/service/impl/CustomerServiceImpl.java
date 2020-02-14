package by.epam.autoshow.service.impl;

import by.epam.autoshow.dao.DaoException;
import by.epam.autoshow.dao.manager.CustomerManger;
import by.epam.autoshow.model.Customer;
import by.epam.autoshow.model.User;
import by.epam.autoshow.service.CustomerService;
import by.epam.autoshow.service.ServiceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {
    private static volatile CustomerServiceImpl INSTANCE;
    private static final Logger logger = LogManager.getLogger();
    private CustomerManger customerManger = CustomerManger.getInstance();

    private CustomerServiceImpl() {

    }

    public static CustomerServiceImpl getInstance() {
        CustomerServiceImpl customerService = INSTANCE;
        if (customerService == null) {
            synchronized (CustomerServiceImpl.class) {
                customerService = INSTANCE;
                if (customerService == null) {
                    INSTANCE = customerService = new CustomerServiceImpl();
                }
            }
        }
        return customerService;
    }

    @Override
    public boolean updateCustomer(User user, Customer customer) throws ServiceException {
        try {
            customerManger.updateCustomer(user, customer);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    //FIXME: change find by id method
    @Override
    public Optional<Customer> findCustomerById(long id) throws ServiceException {
        Optional<Customer> customer = Optional.empty();
        try {
            customer = customerManger.findCustomerByLogin(String.valueOf(id));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return customer;
    }

    @Override
    public Optional<Customer> findCustomerByLogin(String login) throws ServiceException {
        Optional<Customer> customer = Optional.empty();
        try {
            customer = customerManger.findCustomerByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return customer;
    }

    @Override
    public List<Customer> findAllCustomers() throws ServiceException {
        List<Customer> customers = new ArrayList<>();
        try {
           customers = customerManger.findCustomerList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return customers;
    }

    @Override
    public boolean registerCustomer(User user, Customer customer) throws ServiceException {
        try {
            customerManger.insertCustomer(user, customer);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
        return true;
    }
}