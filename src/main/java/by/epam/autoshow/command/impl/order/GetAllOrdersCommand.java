package by.epam.autoshow.command.impl.order;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.Order;
import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.util.provider.PagePathProperty;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.OrderServiceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GetAllOrdersCommand implements ActionCommand {
    private static final String PARAM_ORDER_LIST = "orderList";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        String page = null;
        OrderServiceImpl orderService = OrderServiceImpl.getInstance();
        try {
            List<Order> orders = orderService.findAllOrders();
            logger.debug("ORDER LIST: " + orders);
            content.setRequestAttributes(PARAM_ORDER_LIST, orders);
        } catch (ServiceException e) {
            logger.error(e);
        }
        page = PagePathProvider.getProperty(PagePathProperty.ORDER_OVERVIEW_PAGE_PROPERTY);
        return page;
    }
}
