package by.epam.autoshow.command.impl.customer;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.Customer;
import by.epam.autoshow.model.User;
import by.epam.autoshow.model.UserRole;
import by.epam.autoshow.model.UserStatus;
import by.epam.autoshow.service.CustomerService;
import by.epam.autoshow.util.provider.MessageProperty;
import by.epam.autoshow.util.provider.MessageProvider;
import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.util.provider.PagePathProperty;
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
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        String id = content.getRequestParameter(PARAM_USER_ID);
        String login = content.getRequestParameter(PARAM_USERNAME);
        String password = content.getRequestParameter(PARAM_PASSWORD);
        String status = content.getRequestParameter(PARAM_USER_STATUS);
        String customerId = content.getRequestParameter(PARAM_CUSTOMER_ID);
        String surname = content.getRequestParameter(PARAM_CUSTOMER_SURNAME);
        String name = content.getRequestParameter(PARAM_CUSTOMER_NAME);
        String email = content.getRequestParameter(PARAM_CUSTOMER_EMAIL);
        String phoneNumber = content.getRequestParameter(PARAM_CUSTOMER_PHONE_NUMBER);
        String page = PagePathProvider.getProperty(PagePathProperty.CUSTOMER_EDIT_PAGE_PROPERTY);;
        try {
            Long userId = Long.parseLong(id);
            User user = new User(userId, login, password, UserRole.CLIENT, UserStatus.valueOf(status));
            Customer customer = new Customer(Long.parseLong(customerId), userId, surname, name, email, phoneNumber);
            CustomerService customerService = CustomerServiceImpl.getInstance();
            customerService.updateCustomer(user, customer);
            content.setRequestAttributes(ATTRIBUTE_CUSTOMER_CHANGED,
                    MessageProvider.getProperty(MessageProperty.CUSTOMER_SUCCESSFUL_UPDATE_PROPERTY));
        } catch (ServiceException e) {
            logger.error(e);
        } catch (ValidatorException e) {
            content.setRequestAttributes(ATTRIBUTE_INVALID_CUSTOMER,
                    MessageProvider.getProperty(MessageProperty.INVALID_CUSTOMER_UPDATE_PROPERTY));
            logger.error(e);
        }
        return page;
    }
}
