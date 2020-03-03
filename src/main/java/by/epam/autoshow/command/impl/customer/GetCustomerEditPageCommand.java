package by.epam.autoshow.command.impl.customer;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.Customer;
import by.epam.autoshow.model.User;
import by.epam.autoshow.model.UserStatus;
import by.epam.autoshow.service.CustomerService;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.UserService;
import by.epam.autoshow.service.impl.CustomerServiceImpl;
import by.epam.autoshow.service.impl.UserServiceImpl;
import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.util.provider.JspPagePath;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class GetCustomerEditPageCommand implements ActionCommand {
    private static final String PARAM_USER = "user";
    private static final String PARAM_CUSTOMER = "customer";
    private static final String PARAM_CUSTOMER_ID = "customerId";
    private static final String ATTRIBUTE_USER_STATUS = "userStatusArray";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        String page = null;
        String customerId = content.getRequestParameter(PARAM_CUSTOMER_ID);
        try {
            UserService userService = UserServiceImpl.getInstance();
            CustomerService customerService = CustomerServiceImpl.getInstance();
            Optional<Customer> customer = customerService.findCustomerById(Long.parseLong(customerId));
            logger.debug("Customer: " + customer);
            Long userId = customer.get().getUserId();
            Optional<User> user = userService.findUserById(userId);
            logger.debug("User: " + user);
            content.setRequestAttributes(PARAM_CUSTOMER, customer.get());
            content.setRequestAttributes(PARAM_USER, user.get());
            content.setRequestAttributes(ATTRIBUTE_USER_STATUS, UserStatus.values());
            page = PagePathProvider.getProperty(JspPagePath.CUSTOMER_EDIT_PAGE_PROPERTY);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return page;
    }
}