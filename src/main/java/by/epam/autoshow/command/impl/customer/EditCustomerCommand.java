package by.epam.autoshow.command.impl.customer;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;

import by.epam.autoshow.model.Customer;
import by.epam.autoshow.model.User;
import by.epam.autoshow.model.UserRole;
import by.epam.autoshow.model.UserStatus;
import by.epam.autoshow.service.CustomerService;
import by.epam.autoshow.util.manager.PagePathManager;
import by.epam.autoshow.util.manager.PagePathProperty;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.CustomerServiceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EditCustomerCommand implements ActionCommand {
    private static final String PARAM_USER = "user";
    private static final String PARAM_USER_ID = "userId";
    private static final String PARAM_USERNAME = "username";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_USER_ROLE = "userRole";
    private static final String PARAM_USER_STATUS = "userStatus";
    private static final String PARAM_CUSTOMER = "customer";
    private static final String PARAM_CUSTOMER_ID = "customerId";
    private static final String PARAM_CUSTOMER_SURNAME = "userStatus";
    private static final String PARAM_CUSTOMER_NAME = "userStatus";
    private static final String PARAM_CUSTOMER_EMAIL = "userStatus";
    private static final String PARAM_CUSTOMER_PHONE_NUMBER = "userStatus";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String id = sessionRequestContent.getRequestParameter(PARAM_USER_ID);
        String login = sessionRequestContent.getRequestParameter(PARAM_USERNAME);
        String password = sessionRequestContent.getRequestParameter(PARAM_PASSWORD);
        String status = sessionRequestContent.getRequestParameter(PARAM_USER_STATUS);
        String customerId = sessionRequestContent.getRequestParameter(PARAM_CUSTOMER_ID);
        String surname = sessionRequestContent.getRequestParameter(PARAM_CUSTOMER_SURNAME);
        String name = sessionRequestContent.getRequestParameter(PARAM_CUSTOMER_NAME);
        String email = sessionRequestContent.getRequestParameter(PARAM_CUSTOMER_EMAIL);
        String phoneNumber = sessionRequestContent.getRequestParameter(PARAM_CUSTOMER_PHONE_NUMBER);
        String page = null;
        try {
            Long userId = Long.parseLong(id);
            User user = new User(userId, login, password, UserRole.CLIENT, UserStatus.valueOf(status));
            Customer customer = new Customer(Long.parseLong(customerId), userId, surname, name, email, phoneNumber);
            CustomerService customerService = CustomerServiceImpl.getInstance();
            customerService.updateCustomer(user, customer);
            page = PagePathManager.getProperty(PagePathProperty.CUSTOMER_EDIT_PAGE_PROPERTY);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return page;
    }
}
