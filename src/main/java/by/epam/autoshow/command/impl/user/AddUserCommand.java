package by.epam.autoshow.command.impl.user;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.command.RouteType;
import by.epam.autoshow.command.Router;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.User;
import by.epam.autoshow.model.UserRole;
import by.epam.autoshow.model.UserStatus;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.UserService;
import by.epam.autoshow.service.impl.UserServiceImpl;
import by.epam.autoshow.util.provider.MessagePath;
import by.epam.autoshow.util.provider.MessageProvider;
import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.util.provider.JspPagePath;
import by.epam.autoshow.validation.ValidatorException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddUserCommand implements ActionCommand {
    private static final String PARAM_USERNAME = "username";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_USER_STATUS = "userStatus";
    private static final String ATTRIBUTE_INVALID_USER = "invalidUser";
    private static final String ATTRIBUTE_USER_CHANGED = "successfulUserChange";
    private static final String ATTRIBUTE_EXISTING_LOGIN = "existingLogin";
    private static final String ATTRIBUTE_SERVER_ERROR = "serverError";
    private static final String ATTRIBUTE_USER_LIST = "userList";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(SessionRequestContent content) {
        String login = content.getRequestParameter(PARAM_USERNAME);
        String password = content.getRequestParameter(PARAM_PASSWORD);
        String status = content.getRequestParameter(PARAM_USER_STATUS);
        Router router = null;
        try {
            User user = new User();
            user.setUsername(login);
            user.setPassword(password);
            user.setRole(UserRole.ADMIN);
            user.setStatus(UserStatus.valueOf(status));
            UserService userService = UserServiceImpl.getInstance();
            boolean isRegistered = userService.registerUser(user);
            if (isRegistered) {
                content.setRequestAttributes(ATTRIBUTE_USER_CHANGED,
                        MessageProvider.getProperty(MessagePath.USER_SUCCESSFUL_ADDITION_PROPERTY));
                content.setRequestAttributes(ATTRIBUTE_USER_LIST, userService.findAllUsers());
            } else {
                content.setRequestAttributes(ATTRIBUTE_EXISTING_LOGIN,
                        MessageProvider.getProperty(MessagePath.INVALID_USERNAME_PROPERTY));
            }
            router = new Router(JspPagePath.USERS_PAGE_URL, RouteType.REDIRECT);
        } catch (ServiceException e) {
            logger.error(e);
            content.setRequestAttributes(ATTRIBUTE_SERVER_ERROR,
                    MessageProvider.getProperty(MessagePath.SERVER_ERROR_PROPERTY));
            router = new Router(PagePathProvider.getProperty(JspPagePath.ERROR_PAGE_PROPERTY), RouteType.FORWARD);
        } catch (ValidatorException e) {
            logger.error(e);
            content.setRequestAttributes(ATTRIBUTE_INVALID_USER,
                    MessageProvider.getProperty(MessagePath.INVALID_USER_ADDITION_PROPERTY));
            router = new Router(PagePathProvider.getProperty(JspPagePath.USER_EDIT_PAGE_PROPERTY), RouteType.FORWARD);
        }
        return router;
    }
}