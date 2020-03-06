package by.epam.autoshow.service;

import by.epam.autoshow.model.Order;

import java.util.List;

/**
 * Coordinates tasks and delegates work to collaborations of {@link Order} objects in the
 * dao manager layer
 *
 * @author Daniil Slobin
 * @see by.epam.autoshow.dao.manager.OrderManager
 * @see by.epam.autoshow.service.impl.OrderServiceImpl
 */

public interface OrderService {
    /**
     * Adds order
     *
     * @param order object to insert in data store
     * @return {@code true} if adding order was successful
     * @throws ServiceException if dao manager errors occurs
     */
    boolean addOrder(Order order) throws ServiceException;

    /**
     * Deletes an order
     *
     * @param id order unique identifier
     * @return {@code true} if order deletion was successful
     * @throws ServiceException if dao manager errors occurs
     */
    boolean deleteOrder(long id) throws ServiceException;

    /**
     * Finds orders
     *
     * @return list of orders
     * @throws ServiceException if dao manager errors occurs
     */
    List<Order> findAllOrders() throws ServiceException;

    /**
     * Finds orders by customer id
     *
     * @param customerId customer unique identifier
     * @return list of customer orders
     * @throws ServiceException if dao manager errors occurs
     */
    List<Order> findCustomerOrders(long customerId) throws ServiceException;

    /**
     * Updates order status
     *
     * @param order object to update
     * @return {@code true} if order status was updated successfully
     * @throws ServiceException if dao manager errors occurs
     */
    boolean updateOrderStatus(Order order) throws ServiceException;
}