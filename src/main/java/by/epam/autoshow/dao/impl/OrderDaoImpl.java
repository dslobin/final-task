package by.epam.autoshow.dao.impl;

import by.epam.autoshow.dao.DaoException;
import by.epam.autoshow.dao.OrderDao;
import by.epam.autoshow.dao.SqlColumnName;
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

    private static final String UPDATE_ORDER_STATUS =
            "UPDATE orders SET status = ? WHERE order_id = ?";

    private static final String FIND_ALL =
            "SELECT order_id, service_id, customer_id, date, time, price, status FROM orders";

    private static final String FIND_BY_ID =
            "SELECT order_id, service_id, customer_id, date, time, price, status FROM orders WHERE order_id = ?";

    private static final String FIND_CUSTOMER_ORDERS =
            "SELECT order_id, service_id, date, time, price, status FROM orders WHERE customer_id = ?";

    public OrderDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean insert(Order order) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            editOrderTableRow(order, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return true;
    }

    private void editOrderTableRow(Order order, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setLong(1, order.getCustomerId());
        preparedStatement.setLong(2, order.getServiceId());
        preparedStatement.setDate(3, Date.valueOf(order.getServiceTime().toLocalDate()));
        preparedStatement.setTime(4, Time.valueOf(order.getServiceTime().toLocalTime()));
        preparedStatement.setBigDecimal(5, order.getPrice());
        preparedStatement.setString(6, order.getStatus().name());
    }

    @Override
    public Optional<Order> findById(long id) throws DaoException {
        Order order = new Order();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    order.setOrderId(resultSet.getLong(SqlColumnName.ORDER_ID));
                    order.setCustomerId(resultSet.getLong(SqlColumnName.CUSTOMER_ID));
                    order.setServiceId(resultSet.getLong(SqlColumnName.SERVICE_ID));
                    order.setServiceTime(resultSet.getDate(SqlColumnName.DATE).toLocalDate()
                            .atTime(resultSet.getTime(SqlColumnName.TIME).toLocalTime()));
                    order.setPrice(resultSet.getBigDecimal(SqlColumnName.PRICE));
                    order.setStatus(OrderStatus.valueOf(resultSet.getString(SqlColumnName.STATUS)));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
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
                    Order order = new Order(
                            resultSet.getLong(SqlColumnName.ORDER_ID),
                            resultSet.getLong(SqlColumnName.CUSTOMER_ID),
                            resultSet.getLong(SqlColumnName.SERVICE_ID),
                            resultSet.getDate(SqlColumnName.DATE).toLocalDate()
                                    .atTime(resultSet.getTime(SqlColumnName.TIME).toLocalTime()),
                            resultSet.getBigDecimal(SqlColumnName.PRICE),
                            OrderStatus.valueOf(resultSet.getString(SqlColumnName.STATUS))
                    );
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
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
            throw new DaoException(e);
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
            throw new DaoException(e);
        }
        return true;
    }

    @Override
    public List<Order> findAll() throws DaoException {
        List<Order> orderList = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
            while (resultSet.next()) {
                Order order = new Order(
                        resultSet.getLong(SqlColumnName.ORDER_ID),
                        resultSet.getLong(SqlColumnName.CUSTOMER_ID),
                        resultSet.getLong(SqlColumnName.SERVICE_ID),
                        resultSet.getDate(SqlColumnName.DATE).toLocalDate()
                                .atTime(resultSet.getTime(SqlColumnName.TIME).toLocalTime()),
                        resultSet.getBigDecimal(SqlColumnName.PRICE),
                        OrderStatus.valueOf(resultSet.getString(SqlColumnName.STATUS))
                );
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return orderList;
    }
}