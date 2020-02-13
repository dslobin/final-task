package by.epam.autoshow.command.impl;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.Customer;
import by.epam.autoshow.model.Order;
import by.epam.autoshow.service.CustomerService;
import by.epam.autoshow.service.OrderService;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.CustomerServiceImpl;
import by.epam.autoshow.service.impl.OrderServiceImpl;
import by.epam.autoshow.util.manager.PagePathManager;
import by.epam.autoshow.util.manager.PagePathProperty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class GetProfilePageCommand implements ActionCommand {
    private static final String ATTRIBUTE_USER_LOGIN = "userLogin";
    private static final String ATTRIBUTE_CUSTOMER = "customer";
    private static final String ATTRIBUTE_ORDERS = "customerOrders";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = null;
        String login = (String) sessionRequestContent.getSessionAttributes(ATTRIBUTE_USER_LOGIN);
        try {
            CustomerService customerService = CustomerServiceImpl.getInstance();
            OrderService orderService = OrderServiceImpl.getInstance();
            Optional<Customer> customer = customerService.findCustomerByLogin(login);
            sessionRequestContent.setRequestAttributes(ATTRIBUTE_CUSTOMER, customer.get());
            long id = customer.get().getCustomerId();
            List<Order> orders = orderService.findCustomerOrders(id);
            sessionRequestContent.setRequestAttributes(ATTRIBUTE_ORDERS, orders);
        } catch (ServiceException e) {
            logger.error(e);
        }
        page = PagePathManager.getProperty(PagePathProperty.PROFILE_PAGE_PROPERTY);
        return page;
    }
}
