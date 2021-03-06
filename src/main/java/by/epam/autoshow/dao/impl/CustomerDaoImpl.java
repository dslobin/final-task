package by.epam.autoshow.dao.impl;

import by.epam.autoshow.dao.CustomerDao;
import by.epam.autoshow.dao.DaoException;
import by.epam.autoshow.dao.SqlColumnName;
import by.epam.autoshow.model.Customer;

import java.sql.*;

import java.util.*;

public class CustomerDaoImpl implements CustomerDao {
    private Connection connection;

    private static final String INSERT =
            "INSERT INTO customers (user_id, surname, name, email, phone_number) VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE =
            "UPDATE customers SET user_id = ?, surname = ?, name = ?, email = ?, phone_number = ? WHERE customer_id = ?";

    private static final String FIND_ALL =
            "SELECT customer_id, user_id, surname, customer_id, user_id, surname, name, email, phone_number FROM customers";

    private static final String FIND_BY_ID =
            "SELECT customer_id, user_id, surname, name, email, phone_number FROM customers WHERE customer_id = ?";

    private static final String FIND_BY_USER_ID =
            "SELECT customer_id, user_id, surname, name, email, phone_number FROM customers WHERE user_id = ?";

    private static final String FIND_CUSTOMER_USER_NAMES =
            "SELECT users.username, customers.customer_id, customers.user_id, surname, name, email, phone_number" +
                    " FROM customers" +
                    " INNER JOIN users ON customers.user_id = users.user_id";

    public CustomerDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean insert(Customer customer) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setLong(1, customer.getUserId());
            preparedStatement.setString(2, customer.getSurname());
            preparedStatement.setString(3, customer.getName());
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.setString(5, customer.getPhoneNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error adding customer", e);
        }
        return true;
    }

    @Override
    public Optional<Customer> findById(long customerId) throws DaoException {
        Customer customer = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            customer = getCustomer(customerId, preparedStatement);
        } catch (SQLException e) {
            throw new DaoException("Error finding customer by id", e);
        }
        return Optional.ofNullable(customer);
    }

    private Customer getCustomer(long id, PreparedStatement statement) throws SQLException {
        Customer customer = null;
        statement.setLong(1, id);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                customer = new Customer(
                        resultSet.getLong(SqlColumnName.CUSTOMER_ID),
                        resultSet.getLong(SqlColumnName.USER_ID),
                        resultSet.getString(SqlColumnName.SURNAME),
                        resultSet.getString(SqlColumnName.NAME),
                        resultSet.getString(SqlColumnName.EMAIL),
                        resultSet.getString(SqlColumnName.PHONE_NUMBER)
                );
            }
        }
        return customer;
    }

    @Override
    public Optional<Customer> findByUserId(long userId) throws DaoException {
        Customer customer = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USER_ID)) {
            customer = getCustomer(userId, preparedStatement);

        } catch (SQLException e) {
            throw new DaoException("Error finding customer by user id", e);
        }
        return Optional.ofNullable(customer);
    }

    @Override
    public Customer update(Customer customer) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setLong(1, customer.getUserId());
            preparedStatement.setString(2, customer.getSurname());
            preparedStatement.setString(3, customer.getName());
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.setString(5, customer.getPhoneNumber());
            preparedStatement.setLong(6, customer.getCustomerId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return customer;
    }

    @Override
    public Map<String, Customer> findCustomerUserNames() throws DaoException {
        Map<String, Customer> customers = new HashMap<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_CUSTOMER_USER_NAMES)) {
            String username = null;
            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getLong(SqlColumnName.CUSTOMER_ID),
                        resultSet.getLong(SqlColumnName.USER_ID),
                        resultSet.getString(SqlColumnName.SURNAME),
                        resultSet.getString(SqlColumnName.NAME),
                        resultSet.getString(SqlColumnName.EMAIL),
                        resultSet.getString(SqlColumnName.PHONE_NUMBER)
                );
                username = resultSet.getString(SqlColumnName.USERNAME);
                customers.put(username, customer);
            }
        } catch (SQLException e) {
            throw new DaoException("Error finding customer user names", e);
        }
        return customers;
    }

    @Override
    public List<Customer> findAll() throws DaoException {
        List<Customer> customerList = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getLong(SqlColumnName.CUSTOMER_ID),
                        resultSet.getLong(SqlColumnName.USER_ID),
                        resultSet.getString(SqlColumnName.SURNAME),
                        resultSet.getString(SqlColumnName.NAME),
                        resultSet.getString(SqlColumnName.EMAIL),
                        resultSet.getString(SqlColumnName.PHONE_NUMBER)
                );
                customerList.add(customer);
            }
        } catch (SQLException e) {
            throw new DaoException("Error finding customers", e);
        }
        return customerList;
    }
}