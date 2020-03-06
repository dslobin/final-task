package by.epam.autoshow.dao.impl;

import by.epam.autoshow.dao.DaoException;
import by.epam.autoshow.dao.OrderDao;
import by.epam.autoshow.dao.SqlColumnName;
import by.epam.autoshow.model.AutoShowService;
import by.epam.autoshow.model.Customer;
import by.epam.autoshow.model.Order;
import by.epam.autoshow.model.OrderStatus;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {
    private Connection connection;

    private static final String INSERT =
            "INSERT INTO orders (customer_id, service_id, date, time, price, status) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE =
            "UPDATE orders SET customer_id = ?, service_id = ?, date = ?, time = ?, price = ?, status = ?" +
                    " WHERE order_id = ?";

    private static final String DELETE =
            "DELETE FROM orders WHERE order_id = ?";

    private static final String UPDATE_ORDER_STATUS =
            "UPDATE orders SET status = ? WHERE order_id = ?";

    private static final String FIND_ALL =
            "SELECT orders.order_id, orders.date, orders.time, orders.price, orders.status," +
                    " customers.customer_id, customers.user_id, customers.surname, customers.name, customers.email, customers.phone_number," +
                    " services.service_id, services.title, services.cost, services.description FROM orders" +
                    " JOIN customers ON orders.customer_id = customers.customer_id" +
                    " JOIN services ON orders.service_id = services.service_id";

    private static final String FIND_BY_ID =
            "SELECT orders.order_id, orders.date, orders.time, orders.price, orders.status," +
                    " customers.customer_id, customers.user_id, customers.surname, customers.name, customers.email, customers.phone_number," +
                    " services.service_id, services.title, services.cost, services.description FROM orders" +
                    " JOIN customers ON orders.customer_id = customers.customer_id" +
                    " JOIN services ON orders.service_id = services.service_id" +
                    " WHERE order_id = ?";

    private static final String FIND_CUSTOMER_ORDERS =
            "SELECT orders.order_id, orders.date, orders.time, orders.price, orders.status," +
                    " customers.customer_id, customers.user_id, customers.surname, customers.name, customers.email, customers.phone_number," +
                    " services.service_id, services.title, services.cost, services.description FROM orders" +
                    " JOIN customers ON orders.customer_id = customers.customer_id" +
                    " JOIN services ON orders.service_id = services.service_id" +
                    " WHERE orders.customer_id = ?";

    public OrderDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean insert(Order order) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            editOrderTableRow(order, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error adding order", e);
        }
        return true;
    }

    @Override
    public boolean delete(long id) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error deleting order", e);
        }
        return true;
    }

    @Override
    public Optional<Order> findById(long id) throws DaoException {
        Order order = new Order();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    createOrderFromResultSet(order, resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error finding order by id", e);
        }
        return Optional.of(order);
    }

    @Override
    public List<Order> findByCustomerId(long customerId) throws DaoException {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_CUSTOMER_ORDERS)) {
            preparedStatement.setLong(1, customerId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Order order = new Order();
                    createOrderFromResultSet(order, resultSet);
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error finding order by customer id", e);
        }
        return orders;
    }

    @Override
    public Order update(Order order) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            editOrderTableRow(order, preparedStatement);
            preparedStatement.setLong(7, order.getOrderId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error updating order", e);
        }
        return order;
    }

    @Override
    public boolean updateOrderStatus(Order order) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_STATUS)) {
            preparedStatement.setString(1, order.getStatus().name());
            preparedStatement.setLong(2, order.getOrderId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error updating order status", e);
        }
        return true;
    }

    @Override
    public List<Order> findAll() throws DaoException {
        List<Order> orderList = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
            while (resultSet.next()) {
                Order order = new Order();
                createOrderFromResultSet(order, resultSet);
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException("Error finding orders", e);
        }
        return orderList;
    }

    private void createOrderFromResultSet(Order order, ResultSet resultSet) throws SQLException {
        order.setOrderId(resultSet.getLong(SqlColumnName.ORDER_ID));
        order.setServiceTime(resultSet.getDate(SqlColumnName.DATE).toLocalDate()
                .atTime(resultSet.getTime(SqlColumnName.TIME).toLocalTime()));
        order.setPrice(resultSet.getBigDecimal(SqlColumnName.PRICE));
        order.setStatus(OrderStatus.valueOf(resultSet.getString(SqlColumnName.STATUS)));
        Customer customer = new Customer(
                resultSet.getLong(SqlColumnName.CUSTOMER_ID),
                resultSet.getLong(SqlColumnName.USER_ID),
                resultSet.getString(SqlColumnName.SURNAME),
                resultSet.getString(SqlColumnName.NAME),
                resultSet.getString(SqlColumnName.EMAIL),
                resultSet.getString(SqlColumnName.PHONE_NUMBER)
        );
        order.setCustomer(customer);
        AutoShowService autoShowService = new AutoShowService(
                resultSet.getLong(SqlColumnName.SERVICE_ID),
                resultSet.getString(SqlColumnName.TITLE),
                resultSet.getBigDecimal(SqlColumnName.COST),
                resultSet.getString(SqlColumnName.DESCRIPTION)
        );
        order.setService(autoShowService);
    }

    private void editOrderTableRow(Order order, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setLong(1, order.getCustomer().getCustomerId());
        preparedStatement.setLong(2, order.getService().getServiceId());
        preparedStatement.setDate(3, Date.valueOf(order.getServiceTime().toLocalDate()));
        preparedStatement.setTime(4, Time.valueOf(order.getServiceTime().toLocalTime()));
        preparedStatement.setBigDecimal(5, order.getPrice());
        preparedStatement.setString(6, order.getStatus().name());
    }
}