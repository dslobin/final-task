package by.epam.autoshow.command.impl.order;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.Order;
import by.epam.autoshow.service.OrderService;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.OrderServiceImpl;
import by.epam.autoshow.util.provider.MessageProperty;
import by.epam.autoshow.util.provider.MessageProvider;
import by.epam.autoshow.util.provider.PagePathProperty;
import by.epam.autoshow.util.provider.PagePathProvider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CancelOrderCommand implements ActionCommand {
    private static final String PARAM_ORDER_ID = "orderId";
    private static final String ATTRIBUTE_SERVER_ERROR = "serverError";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        String orderId = content.getRequestParameter(PARAM_ORDER_ID);
        String page = null;
        try {
            OrderService orderService = OrderServiceImpl.getInstance();
            Order order = new Order();
            order.setOrderId(Long.parseLong(orderId));
            orderService.deleteOrder(order);
            page = PagePathProvider.getProperty(PagePathProperty.PROFILE_PAGE_PROPERTY);
        } catch (ServiceException e) {
            logger.error(e);
            content.setRequestAttributes(ATTRIBUTE_SERVER_ERROR,
                    MessageProvider.getProperty(MessageProperty.SERVER_ERROR_PROPERTY));
            page = PagePathProvider.getProperty(PagePathProperty.ERROR_PAGE_PROPERTY);
        }
        return page;
    }
}
