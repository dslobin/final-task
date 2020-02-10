package by.epam.autoshow.command.impl;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.resource.PagePathPropertyProvider;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.customer.Customer;
import by.epam.autoshow.model.user.User;
import by.epam.autoshow.resource.PagePathManager;
import by.epam.autoshow.resource.MessageManager;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.UserServiceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegistrationCommand implements ActionCommand {
    private static final String PARAM_USERNAME = "username";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_SURNAME = "surname";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_PHONE_NUMBER = "phoneNumber";
    private static final String ATTRIBUTE_EXISTING_LOGIN = "existingLogin";
    private static final String INVALID_USERNAME_PROPERTY = "label.invalidUsername";
    private static final String ATTRIBUTE_COMPLETED_REGISTRATION = "completedRegistration";
    private static final String SUCCESSFUL_REGISTRATION_PROPERTY = "label.successfulRegistration";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = null;
        String login = sessionRequestContent.getRequestParameter(PARAM_USERNAME);
        String password = sessionRequestContent.getRequestParameter(PARAM_PASSWORD);
        String surname = sessionRequestContent.getRequestParameter(PARAM_SURNAME);
        String name = sessionRequestContent.getRequestParameter(PARAM_NAME);
        String email = sessionRequestContent.getRequestParameter(PARAM_EMAIL);
        String phoneNumber = sessionRequestContent.getRequestParameter(PARAM_PHONE_NUMBER);
        User user = new User(login, password);
        Customer customer = new Customer(surname, name, email, phoneNumber);
        UserServiceImpl registrationService = new UserServiceImpl();
        try {
            boolean isCustomerRegistered = registrationService.registerCustomer(user, customer);
            if (isCustomerRegistered) {
                page = PagePathManager.getProperty(PagePathPropertyProvider.LOGIN_PAGE_PROPERTY);
                sessionRequestContent.setRequestAttributes(ATTRIBUTE_COMPLETED_REGISTRATION,
                        MessageManager.getProperty(SUCCESSFUL_REGISTRATION_PROPERTY));
            } else {
                sessionRequestContent.setRequestAttributes(ATTRIBUTE_EXISTING_LOGIN,
                        MessageManager.getProperty(INVALID_USERNAME_PROPERTY));
                page = PagePathManager.getProperty(PagePathPropertyProvider.REGISTRATION_PAGE_PROPERTY);
            }
        } catch (ServiceException e) {
            logger.error(e);
        }
        return page;
    }
}
