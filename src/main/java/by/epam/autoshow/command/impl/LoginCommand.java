package by.epam.autoshow.command.impl;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.model.User;
import by.epam.autoshow.util.manager.PagePathPropertyProvider;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.util.manager.PagePathManager;
import by.epam.autoshow.util.manager.MessageManager;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.UserServiceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_USERNAME = "username";
    private static final String PARAM_PASSWORD = "password";
    private static final String ATTRIBUTE_USER_LOGIN = "userLogin";
    private static final String ATTRIBUTE_USER_ROLE = "userRole";
    private static final String ATTRIBUTE_ERROR_MESSAGE = "errorLoginPasswordMessage";
    private static final String ERROR_MESSAGE_PROPERTY = "label.loginError";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = null;
        String login = sessionRequestContent.getRequestParameter(PARAM_USERNAME);
        String password = sessionRequestContent.getRequestParameter(PARAM_PASSWORD);
        boolean isRegistered = false;
        UserServiceImpl userService = new UserServiceImpl();
        try {
            Optional<User> user = userService.authorizeUser(login, password);
            isRegistered = user.isPresent();
            if (isRegistered) {
                sessionRequestContent.setSessionAttributes(ATTRIBUTE_USER_LOGIN, user.get().getUsername());
                sessionRequestContent.setSessionAttributes(ATTRIBUTE_USER_ROLE, user.get().getRole());
                page = PagePathManager.getProperty(PagePathPropertyProvider.HOME_PAGE_PROPERTY);
            } else {
                sessionRequestContent.setRequestAttributes(ATTRIBUTE_ERROR_MESSAGE,
                        MessageManager.getProperty(ERROR_MESSAGE_PROPERTY));
                page = PagePathManager.getProperty(PagePathPropertyProvider.LOGIN_PAGE_PROPERTY);
            }
        } catch (ServiceException e) {
            logger.error(e);
        }
        return page;
    }
}