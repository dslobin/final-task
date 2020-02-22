package by.epam.autoshow.command.impl.user;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.User;
import by.epam.autoshow.model.UserRole;
import by.epam.autoshow.model.UserStatus;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.UserServiceImpl;
import by.epam.autoshow.util.provider.MessageProperty;
import by.epam.autoshow.util.provider.MessageProvider;
import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.util.provider.PagePathProperty;
import by.epam.autoshow.validation.ValidatorException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddUserCommand implements ActionCommand {
    private static final String PARAM_USERNAME = "username";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_USER_STATUS = "userStatus";
    private static final String ATTRIBUTE_INVALID_USER = "invalidUser";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        String page = null;
        String login = content.getRequestParameter(PARAM_USERNAME);
        String password = content.getRequestParameter(PARAM_PASSWORD);
        String status = content.getRequestParameter(PARAM_USER_STATUS);
        try {
            User user = new User();
            user.setUsername(login);
            user.setPassword(password);
            user.setRole(UserRole.ADMIN);
            user.setStatus(UserStatus.valueOf(status));
            UserServiceImpl userService = UserServiceImpl.getInstance();
            userService.registerUser(user);
            page = PagePathProvider.getProperty(PagePathProperty.USER_EDIT_PAGE_PROPERTY);
        } catch (ServiceException e) {
            logger.error(e);
        } catch (ValidatorException e) {
            content.setRequestAttributes(ATTRIBUTE_INVALID_USER,
                    MessageProvider.getProperty(MessageProperty.INVALID_USER_ADDITION_PROPERTY));
            logger.error(e);
        }
        return page;
    }
}
