package by.epam.autoshow.dao.impl;

import by.epam.autoshow.dao.CustomerDao;
import by.epam.autoshow.dao.DaoException;
import by.epam.autoshow.dao.SqlColumnName;
import by.epam.autoshow.model.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

import java.util.*;

@SuppressWarnings("Duplicates")
public class CustomerDaoImpl implements CustomerDao {
    private Connection connection;
    private static final Logger logger = LogManager.getLogger();

    private static final String INSERT =
            "INSERT INTO customers (user_id, surname, name, email, phone_number) VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE =
            "UPDATE customers SET user_id = ?, surname = ?, name = ?, email = ?, phone_number = ? WHERE customer_id = ?";

    private static final String FIND_CUSTOMER_USER_NAMES =
            "SELECT customers.customer_id, customers.user_id, users.username, surname, name, email, phone_number" +
                    " FROM customers INNER JOIN users ON customers.user_id = users.user_id";

    private static final String FIND_ALL =
            "SELECT customer_id, user_id, surname, customer_id, user_id, surname, name, email, phone_number FROM customers";

    private static final String FIND_BY_ID =
            "SELECT customer_id, user_id, surname, name, email, phone_number FROM customers WHERE customer_id = ?";

    private static final String FIND_BY_USER_ID =
            "SELECT customer_id, user_id, surname, name, email, phone_number FROM customers WHERE user_id = ?";

    public CustomerDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean insert(Customer customer) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setLong(1, customer.getUserId());
            preparedStatement.setString(2, customer.getSurname());
            preparedStatement.setString(3, customer.getName());
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.setString(5, customer.getPhoneNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
        return true;
    }

    @Override
    public Optional<Customer> findById(long id) throws DaoException {
        Customer customer = new Customer();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                customer.setCustomerId(resultSet.getLong(SqlColumnName.CUSTOMER_ID));
                customer.setUserId(resultSet.getLong(SqlColumnName.USER_ID));
                customer.setSurname(resultSet.getString(SqlColumnName.SURNAME));
                customer.setName(resultSet.getString(SqlColumnName.NAME));
                customer.setEmail(resultSet.getString(SqlColumnName.EMAIL));
                customer.setPhoneNumber(resultSet.getString(SqlColumnName.PHONE_NUMBER));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
        }
        return Optional.of(customer);
    }

    public Optional<Customer> findByUserId(long id) throws DaoException {
        Customer customer = new Customer();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_BY_USER_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                customer.setCustomerId(resultSet.getLong(SqlColumnName.CUSTOMER_ID));
                customer.setUserId(resultSet.getLong(SqlColumnName.USER_ID));
                customer.setSurname(resultSet.getString(SqlColumnName.SURNAME));
                customer.setName(resultSet.getString(SqlColumnName.NAME));
                customer.setEmail(resultSet.getString(SqlColumnName.EMAIL));
                customer.setPhoneNumber(resultSet.getString(SqlColumnName.PHONE_NUMBER));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
        }
        return Optional.of(customer);
    }

    @Override
    public Customer update(Customer customer) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setLong(1, customer.getUserId());
            preparedStatement.setString(2, customer.getSurname());
            preparedStatement.setString(3, customer.getName());
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.setString(5, customer.getPhoneNumber());
            preparedStatement.setLong(6, customer.getCustomerId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
        return customer;
    }

    @Override
    public Map<String, Customer> findCustomerUserNames() throws DaoException {
        Map<String, Customer> customers = new HashMap<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_CUSTOMER_USER_NAMES);
            String username = null;
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(resultSet.getLong(SqlColumnName.CUSTOMER_ID));
                customer.setUserId(resultSet.getLong(SqlColumnName.USER_ID));
                customer.setSurname(resultSet.getString(SqlColumnName.SURNAME));
                customer.setName(resultSet.getString(SqlColumnName.NAME));
                customer.setEmail(resultSet.getString(SqlColumnName.EMAIL));
                customer.setPhoneNumber(resultSet.getString(SqlColumnName.PHONE_NUMBER));
                username = resultSet.getString(SqlColumnName.USERNAME);
                customers.put(username, customer);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
        }
        return customers;
    }

    @Override
    public List<Customer> findAll() throws DaoException {
        List<Customer> customerList = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(resultSet.getLong(SqlColumnName.CUSTOMER_ID));
                customer.setUserId(resultSet.getLong(SqlColumnName.USER_ID));
                customer.setSurname(resultSet.getString(SqlColumnName.SURNAME));
                customer.setName(resultSet.getString(SqlColumnName.NAME));
                customer.setEmail(resultSet.getString(SqlColumnName.EMAIL));
                customer.setPhoneNumber(resultSet.getString(SqlColumnName.PHONE_NUMBER));
                customerList.add(customer);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
        }
        return customerList;
    }

    @Override
    public void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public void close(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            logger.error(e);
        }
    }
}