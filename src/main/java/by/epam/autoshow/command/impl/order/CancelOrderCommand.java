package by.epam.autoshow.command.impl.order;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.command.RouteType;
import by.epam.autoshow.command.Router;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.service.OrderService;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.OrderServiceImpl;
import by.epam.autoshow.util.provider.MessagePath;
import by.epam.autoshow.util.provider.MessageProvider;
import by.epam.autoshow.util.provider.JspPagePath;
import by.epam.autoshow.util.provider.PagePathProvider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CancelOrderCommand implements ActionCommand {
    private static final String PARAM_ORDER_ID = "orderId";
    private static final String ATTRIBUTE_SERVER_ERROR = "serverError";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(SessionRequestContent content) {
        Long orderId = Long.parseLong(content.getRequestParameter(PARAM_ORDER_ID));
        Router router = null;
        try {
            OrderService orderService = OrderServiceImpl.getInstance();
            orderService.deleteOrder(orderId);
            router = new Router(JspPagePath.PROFILE_PAGE_URL, RouteType.REDIRECT);
        } catch (ServiceException e) {
            logger.error(e);
            content.setRequestAttributes(ATTRIBUTE_SERVER_ERROR,
                    MessageProvider.getProperty(MessagePath.SERVER_ERROR_PROPERTY));
            router = new Router(PagePathProvider.getProperty(JspPagePath.ERROR_PAGE_PROPERTY), RouteType.FORWARD);
        }
        return router;
    }
}