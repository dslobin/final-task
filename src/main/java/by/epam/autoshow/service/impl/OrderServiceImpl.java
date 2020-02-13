package by.epam.autoshow.service.impl;

import by.epam.autoshow.dao.DaoException;
import by.epam.autoshow.dao.manager.OrderManager;
import by.epam.autoshow.model.Order;
import by.epam.autoshow.service.OrderService;
import by.epam.autoshow.service.ServiceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private static volatile OrderServiceImpl INSTANCE;
    private static final Logger logger = LogManager.getLogger();
    private OrderManager orderManager = OrderManager.getInstance();

    private OrderServiceImpl() {

    }

    public static OrderServiceImpl getInstance() {
        OrderServiceImpl orderService = INSTANCE;
        if (orderService == null) {
            synchronized (OrderServiceImpl.class) {
                orderService = INSTANCE;
                if (orderService == null) {
                    INSTANCE = orderService = new OrderServiceImpl();
                }
            }
        }
        return orderService;
    }

    @Override
    public List<Order> findAllOrders() throws ServiceException {
        List<Order> orders = new ArrayList<>();
        try {
            orders = orderManager.findOrderList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    @Override
    public List<Order> findNewOrders() throws ServiceException {
        List<Order> newOrders = new ArrayList<>();
        try {
            newOrders = orderManager.findNewOrders();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return newOrders;
    }

    @Override
    public List<Order> findCustomerOrders(long customerId) throws ServiceException {
        List<Order> customerOrders = new ArrayList<>();
        try {
            customerOrders = orderManager.findCustomerOrders(customerId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return customerOrders;
    }

}