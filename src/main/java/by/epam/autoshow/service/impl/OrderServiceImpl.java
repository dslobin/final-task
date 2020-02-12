package by.epam.autoshow.service.impl;

import by.epam.autoshow.dao.DaoException;
import by.epam.autoshow.dao.impl.OrderDaoImpl;
import by.epam.autoshow.db.ConnectionPool;
import by.epam.autoshow.model.Order;
import by.epam.autoshow.service.OrderService;
import by.epam.autoshow.service.ServiceException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Override
    public List<Order> findAllOrders() throws ServiceException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.getConnection();
        OrderDaoImpl orderDao = new OrderDaoImpl(connection);
        List<Order> orderList = new ArrayList<>();
        try {
            orderList = orderDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return orderList;
    }

    @Override
    public List<Order> findNewOrders() throws ServiceException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.getConnection();
        OrderDaoImpl orderDao = new OrderDaoImpl(connection);
        List<Order> newOrders = new ArrayList<>();
        try {
            newOrders = orderDao.findNewOrders();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return newOrders;
    }

}