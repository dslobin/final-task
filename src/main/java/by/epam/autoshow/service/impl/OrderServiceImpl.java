package by.epam.autoshow.service.impl;

import by.epam.autoshow.dao.manager.ManagerException;
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
    private OrderManager orderManager;
    private static final Logger logger = LogManager.getLogger();

    private OrderServiceImpl() {
        orderManager = OrderManager.getInstance();
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
    public boolean addOrder(Order order) throws ServiceException {
        try {
            orderManager.insertOrder(order);
        } catch (ManagerException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public List<Order> findAllOrders() throws ServiceException {
        List<Order> orders = new ArrayList<>();
        try {
            orders = orderManager.findOrderList();
        } catch (ManagerException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    @Override
    public List<Order> findCustomerOrders(long customerId) throws ServiceException {
        List<Order> customerOrders = new ArrayList<>();
        try {
            customerOrders = orderManager.findCustomerOrders(customerId);
        } catch (ManagerException e) {
            throw new ServiceException(e);
        }
        return customerOrders;
    }

    @Override
    public boolean updateOrderStatus(Order order) throws ServiceException {
        try {
            orderManager.updateOrderStatus(order);
        } catch (ManagerException e) {
            throw new ServiceException(e);
        }
        return true;
    }
}