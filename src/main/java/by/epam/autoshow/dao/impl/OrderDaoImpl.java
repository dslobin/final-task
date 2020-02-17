package by.epam.autoshow.dao.impl;

import by.epam.autoshow.dao.DaoException;
import by.epam.autoshow.dao.OrderDao;
import by.epam.autoshow.dao.SqlColumnName;
import by.epam.autoshow.model.Order;
import by.epam.autoshow.model.OrderStatus;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("Duplicates")
public class OrderDaoImpl implements OrderDao {
    private Connection connection;
    private static final Logger logger = LogManager.getLogger();

    private static final String INSERT =
            "INSERT INTO orders (customer_id, service_id, date, overall_price, status) VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE =
            "UPDATE orders SET customer_id = ?, service_id = ?, date = ?, overall_price = ?, status = ? WHERE order_id = ?";

    private static final String UPDATE_ORDER_STATUS =
            "UPDATE orders SET status = ? WHERE order_id = ?";

    private static final String FIND_ALL =
            "SELECT order_id, service_id, customer_id, date, overall_price, status FROM orders";

    private static final String FIND_BY_ID =
            "SELECT order_id, service_id, customer_id, date, overall_price, status FROM orders WHERE order_id = ?";

    private static final String FIND_NEW_ORDERS =
            "SELECT order_id, service_id, customer_id, date, overall_price, status FROM orders WHERE status = ?";

    private static final String FIND_CUSTOMER_ORDERS =
            "SELECT order_id, service_id, date, overall_price, status FROM orders WHERE customer_id = ?";

    public OrderDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean insert(Order order) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setLong(1, order.getCustomerId());
            preparedStatement.setLong(2, order.getServiceId());
            preparedStatement.setDate(3, Date.valueOf(order.getOrderDate()));
            preparedStatement.setBigDecimal(4, order.getOverallPrice());
            preparedStatement.setString(5, order.getStatus().name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
        return true;
    }

    @Override
    public Optional<Order> findById(long id) throws DaoException {
        Order order = new Order();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order.setOrderId(resultSet.getLong(SqlColumnName.ORDER_ID));
                order.setCustomerId(resultSet.getLong(SqlColumnName.CUSTOMER_ID));
                order.setServiceId(resultSet.getLong(SqlColumnName.SERVICE_ID));
                order.setOrderDate(resultSet.getDate(SqlColumnName.DATE).toLocalDate());
                order.setOverallPrice(resultSet.getBigDecimal(SqlColumnName.OVERALL_PRICE));
                order.setStatus(OrderStatus.valueOf(resultSet.getString(SqlColumnName.STATUS)));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
        }
        return Optional.of(order);
    }

    @Override
    public List<Order> findByCustomerId(long customerId) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Order> orders = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(FIND_CUSTOMER_ORDERS);
            preparedStatement.setLong(1, customerId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getLong(SqlColumnName.ORDER_ID));
                order.setServiceId(resultSet.getLong(SqlColumnName.SERVICE_ID));
                order.setOrderDate(resultSet.getDate(SqlColumnName.DATE).toLocalDate());
                order.setOverallPrice(resultSet.getBigDecimal(SqlColumnName.OVERALL_PRICE));
                order.setStatus(OrderStatus.valueOf(resultSet.getString(SqlColumnName.STATUS)));
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
        }
        return orders;
    }

    @Override
    public Order update(Order order) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setLong(1, order.getCustomerId());
            preparedStatement.setLong(2, order.getServiceId());
            preparedStatement.setDate(3, Date.valueOf(order.getOrderDate()));
            preparedStatement.setBigDecimal(4, order.getOverallPrice());
            preparedStatement.setString(5, order.getStatus().name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
        return order;
    }

    @Override
    public boolean updateOrderStatus(Order order) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_ORDER_STATUS);
            preparedStatement.setString(1, order.getStatus().name());
            preparedStatement.setLong(2, order.getOrderId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
        return true;
    }

    @Override
    public List<Order> findAll() throws DaoException {
        List<Order> orderList = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getLong(SqlColumnName.ORDER_ID));
                order.setServiceId(resultSet.getLong(SqlColumnName.SERVICE_ID));
                order.setCustomerId(resultSet.getLong(SqlColumnName.CUSTOMER_ID));
                order.setOrderDate(resultSet.getDate(SqlColumnName.DATE).toLocalDate());
                order.setOverallPrice(resultSet.getBigDecimal(SqlColumnName.OVERALL_PRICE));
                order.setStatus(OrderStatus.valueOf(resultSet.getString(SqlColumnName.STATUS)));
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
        }
        return orderList;
    }

    public List<Order> findNewOrders() throws DaoException {
        List<Order> orderList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_NEW_ORDERS);
            preparedStatement.setString(1, OrderStatus.NEW.name());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getLong(SqlColumnName.ORDER_ID));
                order.setServiceId(resultSet.getLong(SqlColumnName.SERVICE_ID));
                order.setCustomerId(resultSet.getLong(SqlColumnName.CUSTOMER_ID));
                order.setOrderDate(resultSet.getDate(SqlColumnName.DATE).toLocalDate());
                order.setOverallPrice(resultSet.getBigDecimal(SqlColumnName.OVERALL_PRICE));
                order.setStatus(OrderStatus.valueOf(resultSet.getString(SqlColumnName.STATUS)));
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
        }
        return orderList;
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