package by.epam.autoshow.command.impl.user;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.User;
import by.epam.autoshow.util.manager.PagePathManager;
import by.epam.autoshow.util.manager.PagePathPropertyProvider;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.UserServiceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class EditUserCommand implements ActionCommand {
    private static final String PARAM_USER = "user";
    private static final String PARAM_USER_ID = "userId";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = null;
        UserServiceImpl userService = UserServiceImpl.getInstance();
        String userId = sessionRequestContent.getRequestParameter(PARAM_USER_ID);
        try {
            Optional<User> user = userService.findUserById(Long.parseLong(userId));
            logger.debug("User: " + user);
            sessionRequestContent.setRequestAttributes(PARAM_USER, user.get());
            page = PagePathManager.getProperty(PagePathPropertyProvider.USER_EDIT_FORM_PROPERTY);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return page;
    }
}
