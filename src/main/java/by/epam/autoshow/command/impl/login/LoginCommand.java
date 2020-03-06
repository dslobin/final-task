package by.epam.autoshow.command.impl.login;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.command.RouteType;
import by.epam.autoshow.command.Router;
import by.epam.autoshow.model.User;
import by.epam.autoshow.service.UserService;
import by.epam.autoshow.util.provider.JspPagePath;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.util.provider.MessagePath;
import by.epam.autoshow.util.provider.MessageProvider;
import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.UserServiceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_USERNAME = "username";
    private static final String PARAM_PASSWORD = "password";
    private static final String ATTRIBUTE_SERVER_ERROR = "serverError";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(SessionRequestContent content) {
        String login = content.getRequestParameter(PARAM_USERNAME);
        String password = content.getRequestParameter(PARAM_PASSWORD);
        String page = null;
        boolean isAuthorizationSuccessful = false;
        try {
            UserService userService = UserServiceImpl.getInstance();
            AuthenticationProcessor userRoleProcessor = new UserRoleProcessor(content);
            AuthenticationProcessor userStatusProcessor = new UserStatusProcessor(content);
            AuthenticationProcessor userExistProcessor = new UserExistProcessor(userService, content);
            userExistProcessor.linkWith(userStatusProcessor);
            userStatusProcessor.linkWith(userRoleProcessor);
            User user = new User();
            user.setUsername(login);
            user.setPassword(password);
            isAuthorizationSuccessful = userExistProcessor.check(user);
            if (isAuthorizationSuccessful) {
                page = PagePathProvider.getProperty(JspPagePath.HOME_PAGE_PROPERTY);
            } else {
                page = PagePathProvider.getProperty(JspPagePath.LOGIN_PAGE_PROPERTY);
            }
        } catch (ServiceException e) {
            logger.error(e);
            content.setRequestAttributes(ATTRIBUTE_SERVER_ERROR,
                    MessageProvider.getProperty(MessagePath.SERVER_ERROR_PROPERTY));
            page = PagePathProvider.getProperty(JspPagePath.ERROR_PAGE_PROPERTY);
        }
        Router router = new Router(page, RouteType.FORWARD);
        return router;
    }
}