package by.epam.autoshow.command.impl.user;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.command.RouteType;
import by.epam.autoshow.command.Router;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.User;
import by.epam.autoshow.model.UserRole;
import by.epam.autoshow.model.UserStatus;
import by.epam.autoshow.service.UserService;
import by.epam.autoshow.util.provider.MessagePath;
import by.epam.autoshow.util.provider.MessageProvider;
import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.util.provider.JspPagePath;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.UserServiceImpl;
import by.epam.autoshow.validation.UserDataValidator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EditUserCommand implements ActionCommand {
    private static final String PARAM_USER_ID = "userId";
    private static final String PARAM_USERNAME = "username";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_USER_STATUS = "userStatus";
    private static final String ATTRIBUTE_INVALID_USER = "invalidUser";
    private static final String ATTRIBUTE_SERVER_ERROR = "serverError";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(SessionRequestContent content) {
        Long userId = Long.parseLong(content.getRequestParameter(PARAM_USER_ID));
        String login = content.getRequestParameter(PARAM_USERNAME);
        String password = content.getRequestParameter(PARAM_PASSWORD);
        UserStatus status = UserStatus.valueOf(content.getRequestParameter(PARAM_USER_STATUS));
        Router router = null;
        UserDataValidator userValidator = new UserDataValidator();
        if (userValidator.isUsernameValid(login) && userValidator.isPasswordValid(password)) {
            try {
                UserService userService = UserServiceImpl.getInstance();
                User user = new User(userId, login, password, UserRole.ADMIN, status);
                userService.updateUser(user);
                router = new Router(JspPagePath.USERS_PAGE_URL, RouteType.REDIRECT);
            } catch (ServiceException e) {
                logger.error(e);
                content.setRequestAttributes(ATTRIBUTE_SERVER_ERROR,
                        MessageProvider.getProperty(MessagePath.SERVER_ERROR_PROPERTY));
                router = new Router(PagePathProvider.getProperty(JspPagePath.ERROR_PAGE_PROPERTY), RouteType.FORWARD);
            }
        } else {
            logger.error("Error updating record, user data not valid.");
            content.setRequestAttributes(ATTRIBUTE_INVALID_USER,
                    MessageProvider.getProperty(MessagePath.INVALID_USER_UPDATE_PROPERTY));
            router = new Router(PagePathProvider.getProperty(JspPagePath.USER_EDIT_PAGE_PROPERTY),
                    RouteType.FORWARD);
        }
        return router;
    }
}