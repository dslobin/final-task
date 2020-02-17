package by.epam.autoshow.command.impl.customer;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.Customer;
import by.epam.autoshow.model.User;
import by.epam.autoshow.model.UserRole;
import by.epam.autoshow.model.UserStatus;
import by.epam.autoshow.service.CustomerService;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.CustomerServiceImpl;
import by.epam.autoshow.util.manager.PagePathManager;
import by.epam.autoshow.util.manager.PagePathProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddCustomerCommand implements ActionCommand {
    private static final String PARAM_USERNAME = "username";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_USER_ROLE = "userRole";
    private static final String PARAM_USER_STATUS = "userStatus";
    private static final String PARAM_CUSTOMER_SURNAME = "surname";
    private static final String PARAM_CUSTOMER_NAME = "name";
    private static final String PARAM_CUSTOMER_EMAIL = "email";
    private static final String PARAM_CUSTOMER_PHONE_NUMBER = "phoneNumber";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        String login = content.getRequestParameter(PARAM_USERNAME);
        String password = content.getRequestParameter(PARAM_PASSWORD);
        String status = content.getRequestParameter(PARAM_USER_STATUS);
        String surname = content.getRequestParameter(PARAM_CUSTOMER_SURNAME);
        String name = content.getRequestParameter(PARAM_CUSTOMER_NAME);
        String email = content.getRequestParameter(PARAM_CUSTOMER_EMAIL);
        String phoneNumber = content.getRequestParameter(PARAM_CUSTOMER_PHONE_NUMBER);
        String page = null;
        try {
            User user = new User();
            user.setUsername(login);
            user.setPassword(password);
            user.setRole(UserRole.CLIENT);
            user.setStatus(UserStatus.valueOf(status));
            Customer customer = new Customer(surname, name, email, phoneNumber);
            CustomerService customerService = CustomerServiceImpl.getInstance();
            customerService.registerCustomer(user, customer);
            page = PagePathManager.getProperty(PagePathProperty.CUSTOMER_EDIT_PAGE_PROPERTY);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return page;
    }
}