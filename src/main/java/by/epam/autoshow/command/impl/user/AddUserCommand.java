package by.epam.autoshow.command.impl.user;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.User;
import by.epam.autoshow.model.UserRole;
import by.epam.autoshow.model.UserStatus;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.UserServiceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddUserCommand implements ActionCommand {
    private static final String PARAM_USERNAME = "username";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_USER_ROLE = "userRole";
    private static final String PARAM_USER_STATUS = "userStatus";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = null;
        String login = sessionRequestContent.getRequestParameter(PARAM_USERNAME);
        String password = sessionRequestContent.getRequestParameter(PARAM_PASSWORD);
        String role = sessionRequestContent.getRequestParameter(PARAM_USER_ROLE);
        String status = sessionRequestContent.getRequestParameter(PARAM_USER_STATUS);
        String surname = sessionRequestContent.getRequestParameter(PARAM_USER_STATUS);
        String name = sessionRequestContent.getRequestParameter(PARAM_USER_STATUS);
        String email = sessionRequestContent.getRequestParameter(PARAM_USER_STATUS);
        String phoneNumber = sessionRequestContent.getRequestParameter(PARAM_USER_STATUS);
        try {
            User user = new User();
            user.setUsername(login);
            user.setPassword(password);
            user.setRole(UserRole.valueOf(role));
            user.setStatus(UserStatus.valueOf(status));
            UserServiceImpl userService = new UserServiceImpl();
            userService.registerUser(user);
            if (UserRole.CLIENT.equals(UserRole.valueOf(role))) {

            }

        } catch (ServiceException e) {
            logger.error(e);
        }
        return page;
    }
}
