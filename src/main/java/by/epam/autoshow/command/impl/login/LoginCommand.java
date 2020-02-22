package by.epam.autoshow.command.impl.login;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.model.User;
import by.epam.autoshow.service.UserService;
import by.epam.autoshow.util.manager.PagePathProperty;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.util.manager.PagePathManager;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.UserServiceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_USERNAME = "username";
    private static final String PARAM_PASSWORD = "password";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        String login = content.getRequestParameter(PARAM_USERNAME);
        String password = content.getRequestParameter(PARAM_PASSWORD);
        String page = null;
        boolean authenticationResult = false;
        try {
            UserService userService = UserServiceImpl.getInstance();
            AuthenticationProcessor userRoleProcessor = new UserRoleProcessor(content);
            AuthenticationProcessor userStatusProcessor = new UserStatusProcessor(content);
            AuthenticationProcessor userExistProcessor = new UserExistProcessor(userService, content);
            userExistProcessor.linkWith(userStatusProcessor);
            userStatusProcessor.linkWith(userRoleProcessor);
            User user = new User(login, password);
            authenticationResult = userExistProcessor.check(user);
            if (authenticationResult) {
                page = PagePathManager.getProperty(PagePathProperty.HOME_PAGE_PROPERTY);
            } else {
                page = PagePathManager.getProperty(PagePathProperty.LOGIN_PAGE_PROPERTY);
            }
        } catch (ServiceException e) {
            logger.error(e);
        }
        return page;
    }
}