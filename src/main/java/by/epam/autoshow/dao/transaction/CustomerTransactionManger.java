package by.epam.autoshow.dao.transaction;

import by.epam.autoshow.dao.DaoException;
import by.epam.autoshow.dao.impl.CustomerDaoImpl;
import by.epam.autoshow.dao.impl.UserDaoImpl;
import by.epam.autoshow.model.customer.Customer;
import by.epam.autoshow.model.user.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Optional;

public class CustomerTransactionManger {
    private Connection connection;
    private static final Logger logger = LogManager.getLogger();

    public CustomerTransactionManger(Connection connection) {
        this.connection = connection;
    }

    public void insertCustomer(User user, Customer customer) throws DaoException, SQLException {
        UserDaoImpl userDao = new UserDaoImpl(connection);
        CustomerDaoImpl customerDao = new CustomerDaoImpl(connection);
        try {
            connection.setAutoCommit(false);
            userDao.insert(user);
            Optional<User> newUser = userDao.findByUsername(user.getUsername());
            if (newUser.isPresent()) {
                Long userId = newUser.get().getUserId();
                customer.setUserId(userId);
                customerDao.insert(customer);
            }
        } catch (DaoException e) {
            connection.rollback();
            throw new DaoException("Transaction failure, customer not inserted", e);
        } catch (SQLException e) {
            throw new DaoException("Failed to set autocommit 'false' value", e);
        } finally {
            try {
                if (!connection.getAutoCommit()) {
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                logger.error(e);
            }
        }

    }
}
