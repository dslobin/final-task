package by.epam.autoshow.command.impl.customer;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.command.RouteType;
import by.epam.autoshow.command.Router;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.Customer;
import by.epam.autoshow.model.User;
import by.epam.autoshow.model.UserRole;
import by.epam.autoshow.model.UserStatus;
import by.epam.autoshow.service.CustomerService;
import by.epam.autoshow.util.provider.*;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.CustomerServiceImpl;
import by.epam.autoshow.validation.ValidatorException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EditCustomerCommand implements ActionCommand {
    private static final String PARAM_USER_ID = "userId";
    private static final String PARAM_USERNAME = "username";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_USER_STATUS = "userStatus";
    private static final String PARAM_CUSTOMER_ID = "customerId";
    private static final String PARAM_CUSTOMER_SURNAME = "surname";
    private static final String PARAM_CUSTOMER_NAME = "name";
    private static final String PARAM_CUSTOMER_EMAIL = "email";
    private static final String PARAM_CUSTOMER_PHONE_NUMBER = "phoneNumber";
    private static final String ATTRIBUTE_INVALID_CUSTOMER = "invalidCustomer";
    private static final String ATTRIBUTE_CUSTOMER_CHANGED = "successfulCustomerChange";
    private static final String ATTRIBUTE_EXISTING_LOGIN = "existingLogin";
    private static final String ATTRIBUTE_SERVER_ERROR = "serverError";
    private static final String ATTRIBUTE_CUSTOMERS_MAP = "customerMap";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(SessionRequestContent content) {
        String id = content.getRequestParameter(PARAM_USER_ID);
        String login = content.getRequestParameter(PARAM_USERNAME);
        String password = content.getRequestParameter(PARAM_PASSWORD);
        String status = content.getRequestParameter(PARAM_USER_STATUS);
        String customerId = content.getRequestParameter(PARAM_CUSTOMER_ID);
        String surname = content.getRequestParameter(PARAM_CUSTOMER_SURNAME);
        String name = content.getRequestParameter(PARAM_CUSTOMER_NAME);
        String email = content.getRequestParameter(PARAM_CUSTOMER_EMAIL);
        String phoneNumber = content.getRequestParameter(PARAM_CUSTOMER_PHONE_NUMBER);
        Router router = null;
        try {
            Long userId = Long.parseLong(id);
            User user = new User(userId, login, password, UserRole.CLIENT, UserStatus.valueOf(status));
            Customer customer = new Customer(Long.parseLong(customerId), userId, surname, name, email, phoneNumber);
            CustomerService customerService = CustomerServiceImpl.getInstance();
            boolean isUpdated = customerService.updateCustomer(user, customer);
            if (isUpdated) {
                content.setRequestAttributes(ATTRIBUTE_CUSTOMER_CHANGED,
                        MessageProvider.getProperty(MessagePath.CUSTOMER_SUCCESSFUL_UPDATE_PROPERTY));
                content.setRequestAttributes(ATTRIBUTE_CUSTOMERS_MAP, customerService.findCustomerUserNames());
            } else {
                content.setRequestAttributes(ATTRIBUTE_EXISTING_LOGIN,
                        MessageProvider.getProperty(MessagePath.INVALID_USERNAME_PROPERTY));
            }
            router = new Router(JspPagePath.CUSTOMERS_PAGE_URL, RouteType.REDIRECT);
        } catch (ServiceException e) {
            logger.error(e);
            content.setRequestAttributes(ATTRIBUTE_SERVER_ERROR,
                    MessageProvider.getProperty(MessagePath.SERVER_ERROR_PROPERTY));
            router = new Router(PagePathProvider.getProperty(JspPagePath.ERROR_PAGE_PROPERTY), RouteType.FORWARD);
        } catch (ValidatorException e) {
            logger.error(e);
            content.setRequestAttributes(ATTRIBUTE_INVALID_CUSTOMER,
                    MessageProvider.getProperty(MessagePath.INVALID_CUSTOMER_UPDATE_PROPERTY));
            router = new Router(PagePathProvider.getProperty(JspPagePath.CUSTOMER_EDIT_PAGE_PROPERTY),
                    RouteType.FORWARD);
        }
        return router;
    }
}