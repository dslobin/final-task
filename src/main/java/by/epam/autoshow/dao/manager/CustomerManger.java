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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public void insertCustomer(User user, Customer customer) throws DaoException, SQLException {
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
            throw new DaoException("Transaction failure, customer not inserted", e);
        } finally {
            close(connection);
        }
    }

    public List<Customer> findCustomerList() throws DaoException {
        Connection connection = getConnection();
        List<Customer> customerList = new ArrayList<>();
        try {
            CustomerDaoImpl customerDao = new CustomerDaoImpl(connection);
            customerList = customerDao.findAll();
        } catch (DaoException e) {
            throw new DaoException(e);
        } finally {
            close(connection);
        }
        return customerList;
    }
}
