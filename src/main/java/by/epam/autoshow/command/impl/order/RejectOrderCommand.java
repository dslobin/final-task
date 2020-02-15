package by.epam.autoshow.command.impl.order;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.Order;
import by.epam.autoshow.model.OrderStatus;
import by.epam.autoshow.service.OrderService;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.OrderServiceImpl;
import by.epam.autoshow.util.manager.PagePathManager;
import by.epam.autoshow.util.manager.PagePathProperty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RejectOrderCommand implements ActionCommand {
    private static final String PARAM_ORDER_ID = "orderId";
    private static final String PARAM_ORDER_LIST = "orderList";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        String page = null;
        String orderId = content.getRequestParameter(PARAM_ORDER_ID);
        try {
            Order order = new Order();
            order.setOrderId(Long.parseLong(orderId));
            order.setStatus(OrderStatus.REJECTED);
            OrderService orderService = OrderServiceImpl.getInstance();
            orderService.updateOrderStatus(order);
            List<Order> orders = orderService.findAllOrders();
            content.setRequestAttributes(PARAM_ORDER_LIST, orders);
        } catch (ServiceException e) {
            logger.error(e);
        }
        page = PagePathManager.getProperty(PagePathProperty.ORDER_OVERVIEW_PAGE_PROPERTY);
        return page;
    }
}
