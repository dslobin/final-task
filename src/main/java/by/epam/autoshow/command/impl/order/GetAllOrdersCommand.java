package by.epam.autoshow.command.impl.order;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.Order;
import by.epam.autoshow.util.manager.PagePathManager;
import by.epam.autoshow.util.manager.PagePathProperty;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.OrderServiceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GetAllOrdersCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static final String PARAM_ORDER_LIST = "orderList";

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = null;
        OrderServiceImpl orderService = OrderServiceImpl.getInstance();
        try {
            List<Order> orders = orderService.findNewOrders();
            logger.debug("ORDER LIST: " + orders);
            sessionRequestContent.setRequestAttributes(PARAM_ORDER_LIST, orders);
            page = PagePathManager.getProperty(PagePathProperty.ORDER_OVERVIEW_PAGE_PROPERTY);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return page;
    }
}
