package by.epam.autoshow.command.impl;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.command.RouteType;
import by.epam.autoshow.command.Router;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.Customer;
import by.epam.autoshow.model.User;
import by.epam.autoshow.model.UserRole;
import by.epam.autoshow.model.UserStatus;
import by.epam.autoshow.service.CustomerService;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.CustomerServiceImpl;
import by.epam.autoshow.util.provider.MessagePath;
import by.epam.autoshow.util.provider.MessageProvider;
import by.epam.autoshow.util.provider.JspPagePath;
import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.validation.CustomerDataValidator;
import by.epam.autoshow.validation.UserDataValidator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegistrationCommand implements ActionCommand {
    private static final String PARAM_USERNAME = "username";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_SURNAME = "surname";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_PHONE_NUMBER = "phoneNumber";
    private static final String ATTRIBUTE_INVALID_CUSTOMER = "invalidCustomer";
    private static final String ATTRIBUTE_EXISTING_LOGIN = "existingLogin";
    private static final String ATTRIBUTE_COMPLETED_REGISTRATION = "completedRegistration";
    private static final String ATTRIBUTE_SERVER_ERROR = "serverError";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(SessionRequestContent content) {
        String surname = content.getRequestParameter(PARAM_SURNAME);
        String name = content.getRequestParameter(PARAM_NAME);
        String email = content.getRequestParameter(PARAM_EMAIL);
        String phoneNumber = content.getRequestParameter(PARAM_PHONE_NUMBER);
        String login = content.getRequestParameter(PARAM_USERNAME);
        String password = content.getRequestParameter(PARAM_PASSWORD);
        String page = PagePathProvider.getProperty(JspPagePath.LOGIN_PAGE_PROPERTY);
        CustomerDataValidator customerValidator = new CustomerDataValidator();
        UserDataValidator userValidator = new UserDataValidator();
        if (userValidator.isUsernameValid(login) && userValidator.isPasswordValid(password) &&
                customerValidator.isNameValid(name) && customerValidator.isSurnameValid(surname) &&
                customerValidator.isEmailValid(email) && customerValidator.isPhoneNumberValid(phoneNumber)) {
            try {
                User user = new User(login, password, UserRole.CLIENT, UserStatus.ACTIVE);
                Customer customer = new Customer(surname, name, email, phoneNumber);
                CustomerService customerService = CustomerServiceImpl.getInstance();
                boolean isRegistered = customerService.registerCustomer(user, customer);
                if (isRegistered) {
                    content.setRequestAttributes(ATTRIBUTE_COMPLETED_REGISTRATION,
                            MessageProvider.getProperty(MessagePath.SUCCESSFUL_REGISTRATION_PROPERTY));
                } else {
                    content.setRequestAttributes(ATTRIBUTE_EXISTING_LOGIN,
                            MessageProvider.getProperty(MessagePath.INVALID_USERNAME_PROPERTY));
                }
            } catch (ServiceException e) {
                logger.error(e);
                content.setRequestAttributes(ATTRIBUTE_SERVER_ERROR,
                        MessageProvider.getProperty(MessagePath.SERVER_ERROR_PROPERTY));
                page = PagePathProvider.getProperty(JspPagePath.REGISTRATION_PAGE_PROPERTY);
            }
        } else {
            logger.error("Error adding record, customer data not valid.");
            content.setRequestAttributes(ATTRIBUTE_INVALID_CUSTOMER,
                    MessageProvider.getProperty(MessagePath.INVALID_CUSTOMER_ADDITION_PROPERTY));
            page = PagePathProvider.getProperty(JspPagePath.REGISTRATION_PAGE_PROPERTY);
        }
        Router router = new Router(page, RouteType.FORWARD);
        return router;
    }
}