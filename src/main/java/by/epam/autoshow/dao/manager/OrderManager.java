package by.epam.autoshow.dao.manager;

import by.epam.autoshow.dao.DaoException;
import by.epam.autoshow.dao.impl.OrderDaoImpl;
import by.epam.autoshow.model.Order;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;

public class OrderManager extends DaoManager {
    private static volatile OrderManager INSTANCE;
    private static final Logger logger = LogManager.getLogger();

    private OrderManager() {

    }

    public static OrderManager getInstance() {
        OrderManager orderManager = INSTANCE;
        if (orderManager == null) {
            synchronized (OrderManager.class) {
                orderManager = INSTANCE;
                if (orderManager == null) {
                    INSTANCE = orderManager = new OrderManager();
                }
            }
        }
        return orderManager;
    }

    public boolean insertOrder(Order order) throws ManagerException {
        Connection connection = getConnection();
        try {
            OrderDaoImpl orderDao = new OrderDaoImpl(connection);
            orderDao.insert(order);
        } catch (DaoException e) {
            throw new ManagerException(e);
        } finally {
            close(connection);
        }
        return true;
    }

    public boolean deleteOrder(long id) throws ManagerException {
        Connection connection = getConnection();
        try {
            OrderDaoImpl orderDao = new OrderDaoImpl(connection);
            orderDao.delete(id);
        } catch (DaoException e) {
            throw new ManagerException(e);
        } finally {
            close(connection);
        }
        return true;
    }

    public List<Order> findOrderList() throws ManagerException {
        Connection connection = getConnection();
        List<Order> orderList = new ArrayList<>();
        try {
            OrderDaoImpl orderDao = new OrderDaoImpl(connection);
            orderList = orderDao.findAll();
        } catch (DaoException e) {
            throw new ManagerException(e);
        } finally {
            close(connection);
        }
        return orderList;
    }

    public List<Order> findCustomerOrders(long customerId) throws ManagerException {
        Connection connection = getConnection();
        List<Order> customerOrders = new ArrayList<>();
        try {
            OrderDaoImpl orderDao = new OrderDaoImpl(connection);
            customerOrders = orderDao.findByCustomerId(customerId);
        } catch (DaoException e) {
            throw new ManagerException(e);
        } finally {
            close(connection);
        }
        return customerOrders;
    }

    public boolean updateOrderStatus(Order order) throws ManagerException {
        Connection connection = getConnection();
        try {
            OrderDaoImpl orderDao = new OrderDaoImpl(connection);
            orderDao.updateOrderStatus(order);
        } catch (DaoException e) {
            throw new ManagerException(e);
        } finally {
            close(connection);
        }
        return true;
    }
}