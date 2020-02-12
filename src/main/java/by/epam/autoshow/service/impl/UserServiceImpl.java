package by.epam.autoshow.service.impl;

import by.epam.autoshow.dao.DaoException;
import by.epam.autoshow.dao.impl.UserDaoImpl;
import by.epam.autoshow.dao.transaction.CustomerTransactionManger;
import by.epam.autoshow.db.ConnectionPool;
import by.epam.autoshow.model.Customer;
import by.epam.autoshow.model.User;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.UserService;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    public boolean registerCustomer(User user, Customer customer) throws ServiceException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.getConnection();
        try {
            CustomerTransactionManger customerTransaction = new CustomerTransactionManger(connection);
            customerTransaction.insertCustomer(user, customer);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return true;
    }

    public boolean registerUser(User user) throws ServiceException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.getConnection();
        UserDaoImpl userDao = new UserDaoImpl(connection);
        try {
            userDao.insert(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return true;
    }

    @Override
    public Optional<User> findUserById(long id) throws ServiceException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.getConnection();
        UserDaoImpl userDao = new UserDaoImpl(connection);
        Optional<User> user = Optional.empty();
        try {
            user = userDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return user;
    }

    @Override
    public Optional<User> authorizeUser(String login, String password) throws ServiceException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.getConnection();
        UserDaoImpl userDao = new UserDaoImpl(connection);
        User user = new User();
        user.setUsername(login);
        user.setPassword(password);
        Optional<User> registeredUser = Optional.empty();
        try {
           registeredUser = userDao.authorizeUser(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return registeredUser;
    }

    @Override
    public List<User> findAllUsers() throws ServiceException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.getConnection();
        UserDaoImpl userDao = new UserDaoImpl(connection);
        List<User> userList = new ArrayList<>();
        try {
            userList = userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return userList;
    }
}