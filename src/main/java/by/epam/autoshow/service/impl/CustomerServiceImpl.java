package by.epam.autoshow.service.impl;

import by.epam.autoshow.dao.DaoException;
import by.epam.autoshow.dao.impl.CustomerDaoImpl;
import by.epam.autoshow.db.ConnectionPool;
import by.epam.autoshow.model.customer.Customer;
import by.epam.autoshow.service.CustomerService;
import by.epam.autoshow.service.ServiceException;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {

    @Override
    public Optional<Customer> findCustomerById(long id) throws ServiceException {
        return Optional.empty();
    }

    @Override
    public List<Customer> findAllCustomers() throws ServiceException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.getConnection();
        CustomerDaoImpl customerDao = new CustomerDaoImpl(connection);
        List<Customer> customerList = new ArrayList<>();
        try {
            customerList = customerDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return customerList;
    }
}
