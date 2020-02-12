package by.epam.autoshow.service;

import by.epam.autoshow.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAllOrders() throws ServiceException;

    List<Order> findNewOrders() throws ServiceException;
}