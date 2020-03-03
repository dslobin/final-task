package by.epam.autoshow.command.impl;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.command.RouteType;
import by.epam.autoshow.command.Router;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.Customer;
import by.epam.autoshow.model.Order;
import by.epam.autoshow.service.CustomerService;
import by.epam.autoshow.service.OrderService;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.CustomerServiceImpl;
import by.epam.autoshow.service.impl.OrderServiceImpl;
import by.epam.autoshow.util.provider.MessagePath;
import by.epam.autoshow.util.provider.MessageProvider;
import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.util.provider.JspPagePath;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class GetProfilePageCommand implements ActionCommand {
    private static final String ATTRIBUTE_USER_LOGIN = "userLogin";
    private static final String ATTRIBUTE_CUSTOMER = "customer";
    private static final String ATTRIBUTE_ORDERS = "customerOrders";
    private static final String ATTRIBUTE_ERROR = "error";
    private static final String ATTRIBUTE_SERVER_ERROR = "serverError";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(SessionRequestContent content) {
        String login = (String) content.getSessionAttributes(ATTRIBUTE_USER_LOGIN);
        String page = PagePathProvider.getProperty(JspPagePath.PROFILE_PAGE_PROPERTY);
        try {
            CustomerService customerService = CustomerServiceImpl.getInstance();
            OrderService orderService = OrderServiceImpl.getInstance();
            Optional<Customer> customer = customerService.findCustomerByLogin(login);
            if (customer.isPresent()) {
                long customerId = customer.get().getCustomerId();
                List<Order> orders = orderService.findCustomerOrders(customerId);
                content.setRequestAttributes(ATTRIBUTE_CUSTOMER, customer.get());
                content.setRequestAttributes(ATTRIBUTE_ORDERS, orders);
            } else {
                content.setRequestAttributes(ATTRIBUTE_ERROR,
                        MessageProvider.getProperty(MessagePath.PROFILE_ERROR_PROPERTY));
            }
        } catch (ServiceException e) {
            logger.error(e);
            content.setRequestAttributes(ATTRIBUTE_SERVER_ERROR,
                    MessageProvider.getProperty(MessagePath.SERVER_ERROR_PROPERTY));
            page = PagePathProvider.getProperty(JspPagePath.ERROR_PAGE_PROPERTY);
        }
        Router router = new Router(page, RouteType.FORWARD);
        return router;
    }
}