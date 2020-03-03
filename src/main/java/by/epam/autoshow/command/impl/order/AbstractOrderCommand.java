package by.epam.autoshow.command.impl.order;

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

import java.util.List;
import java.util.Optional;

abstract class AbstractOrderCommand {
    private static final String ATTRIBUTE_USER_LOGIN = "userLogin";
    private static final String ATTRIBUTE_CUSTOMER = "customer";
    private static final String ATTRIBUTE_ORDERS = "customerOrders";
    private static final String ATTRIBUTE_SERVER_ERROR = "serverError";

    AbstractOrderCommand() {
    }

    boolean fillInProfileData(SessionRequestContent content) throws ServiceException {
        String login = (String) content.getSessionAttributes(ATTRIBUTE_USER_LOGIN);
        CustomerService customerService = CustomerServiceImpl.getInstance();
        OrderService orderService = OrderServiceImpl.getInstance();
        Optional<Customer> customer = customerService.findCustomerByLogin(login);
        if (customer.isPresent()) {
            long customerId = customer.get().getCustomerId();
            List<Order> orders = orderService.findCustomerOrders(customerId);
            content.setRequestAttributes(ATTRIBUTE_CUSTOMER, customer.get());
            content.setRequestAttributes(ATTRIBUTE_ORDERS, orders);
        } else {
            content.setRequestAttributes(ATTRIBUTE_SERVER_ERROR,
                    MessageProvider.getProperty(MessagePath.SERVER_ERROR_PROPERTY));
        }
        return customer.isPresent();
    }
}