package by.epam.autoshow.command.impl;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.Customer;
import by.epam.autoshow.model.User;
import by.epam.autoshow.service.CustomerService;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.CustomerServiceImpl;
import by.epam.autoshow.util.provider.MessageProperty;
import by.epam.autoshow.util.provider.MessageProvider;
import by.epam.autoshow.util.provider.PagePathProperty;
import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.validation.ValidatorException;

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

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        String page = null;
        String login = content.getRequestParameter(PARAM_USERNAME);
        String password = content.getRequestParameter(PARAM_PASSWORD);
        String surname = content.getRequestParameter(PARAM_SURNAME);
        String name = content.getRequestParameter(PARAM_NAME);
        String email = content.getRequestParameter(PARAM_EMAIL);
        String phoneNumber = content.getRequestParameter(PARAM_PHONE_NUMBER);
        try {
            User user = new User(login, password);
            Customer customer = new Customer(surname, name, email, phoneNumber);
            CustomerService customerService = CustomerServiceImpl.getInstance();
            boolean isCustomerRegistered = customerService.registerCustomer(user, customer);
            if (isCustomerRegistered) {
                page = PagePathProvider.getProperty(PagePathProperty.LOGIN_PAGE_PROPERTY);
                content.setRequestAttributes(ATTRIBUTE_COMPLETED_REGISTRATION,
                        PagePathProvider.getProperty(MessageProperty.SUCCESSFUL_REGISTRATION_PROPERTY));
            } else {
                content.setRequestAttributes(ATTRIBUTE_EXISTING_LOGIN,
                        MessageProvider.getProperty(MessageProperty.INVALID_USERNAME_PROPERTY));
                page = PagePathProvider.getProperty(PagePathProperty.REGISTRATION_PAGE_PROPERTY);
            }
        } catch (ServiceException e) {
            logger.error(e);
        } catch (ValidatorException e) {
            content.setRequestAttributes(ATTRIBUTE_INVALID_CUSTOMER,
                    MessageProvider.getProperty(MessageProperty.INVALID_CUSTOMER_ADDITION_PROPERTY));
            logger.error(e);
        }
        return page;
    }
}
