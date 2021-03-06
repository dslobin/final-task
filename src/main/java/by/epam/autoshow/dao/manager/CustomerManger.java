package by.epam.autoshow.dao.manager;

import by.epam.autoshow.dao.DaoException;
import by.epam.autoshow.dao.impl.CustomerDaoImpl;
import by.epam.autoshow.dao.impl.UserDaoImpl;
import by.epam.autoshow.model.Customer;
import by.epam.autoshow.model.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.*;

public class CustomerManger extends DaoManager {
    private static volatile CustomerManger INSTANCE;
    private static final Logger logger = LogManager.getLogger();

    private CustomerManger() {

    }

    public static CustomerManger getInstance() {
        CustomerManger customerManger = INSTANCE;
        if (customerManger == null) {
            synchronized (CustomerManger.class) {
                customerManger = INSTANCE;
                if (customerManger == null) {
                    INSTANCE = customerManger = new CustomerManger();
                }
            }
        }
        return customerManger;
    }

    public void insertCustomer(User user, Customer customer) throws ManagerException, SQLException {
        Connection connection = getTXNConnection();
        try {
            UserDaoImpl userDao = new UserDaoImpl(connection);
            CustomerDaoImpl customerDao = new CustomerDaoImpl(connection);
            userDao.insert(user);
            Optional<User> newUser = userDao.findByUsername(user.getUsername());
            Long userId = newUser.get().getUserId();
            customer.setUserId(userId);
            customerDao.insert(customer);
            connection.commit();
        } catch (DaoException e) {
            connection.rollback();
            throw new ManagerException("Transaction failure, customer not inserted", e);
        } finally {
            close(connection);
        }
    }

    public Optional<Customer> findCustomerByLogin(String login) throws ManagerException {
        Connection connection = getConnection();
        Optional<Customer> customer = Optional.empty();
        try {
            UserDaoImpl userDao = new UserDaoImpl(connection);
            CustomerDaoImpl customerDao = new CustomerDaoImpl(connection);
            Optional<User> searchedUser = userDao.findByUsername(login);
            Long userId = searchedUser.get().getUserId();
            customer = customerDao.findByUserId(userId);
        } catch (DaoException e) {
            throw new ManagerException(e);
        } finally {
            close(connection);
        }
        return customer;
    }

    public Optional<Customer> findById(long id) throws ManagerException {
        Connection connection = getConnection();
        Optional<Customer> customer = Optional.empty();
        try {
            CustomerDaoImpl customerDao = new CustomerDaoImpl(connection);
            customer = customerDao.findById(id);
        } catch (DaoException e) {
            throw new ManagerException(e);
        } finally {
            close(connection);
        }
        return customer;
    }

    public List<Customer> findCustomerList() throws ManagerException {
        Connection connection = getConnection();
        List<Customer> customerList = new ArrayList<>();
        try {
            CustomerDaoImpl customerDao = new CustomerDaoImpl(connection);
            customerList = customerDao.findAll();
        } catch (DaoException e) {
            throw new ManagerException(e);
        } finally {
            close(connection);
        }
        return customerList;
    }

    public boolean updateCustomer(User user, Customer customer) throws ManagerException, SQLException {
        Connection connection = getTXNConnection();
        try {
            UserDaoImpl userDao = new UserDaoImpl(connection);
            CustomerDaoImpl customerDao = new CustomerDaoImpl(connection);
            userDao.update(user);
            customerDao.update(customer);
            connection.commit();
        } catch (DaoException e) {
            connection.rollback();
            throw new ManagerException("Transaction failure, customer not updated", e);
        } finally {
            close(connection);
        }
        return true;
    }

    public Map<String, Customer> findCustomerUserNames() throws ManagerException {
        Connection connection = getConnection();
        Map<String, Customer> customers = new HashMap<>();
        try {
            CustomerDaoImpl customerDao = new CustomerDaoImpl(connection);
            customers = customerDao.findCustomerUserNames();
        } catch (DaoException e) {
            throw new ManagerException(e);
        } finally {
            close(connection);
        }
        return customers;
    }
}