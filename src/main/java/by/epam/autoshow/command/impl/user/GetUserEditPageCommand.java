package by.epam.autoshow.command.impl.user;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.User;
import by.epam.autoshow.model.UserStatus;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.UserServiceImpl;
import by.epam.autoshow.util.manager.PagePathManager;
import by.epam.autoshow.util.manager.PagePathProperty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class GetUserEditPageCommand implements ActionCommand {
    private static final String PARAM_USER = "user";
    private static final String PARAM_USER_ID = "userId";
    private static final String ATTRIBUTE_USER_STATUS_VALUES = "userStatusArray";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = null;
        String userId = sessionRequestContent.getRequestParameter(PARAM_USER_ID);
        try {
            UserServiceImpl userService = UserServiceImpl.getInstance();
            Optional<User> user = userService.findUserById(Long.parseLong(userId));
            logger.debug("User: " + user);
            sessionRequestContent.setRequestAttributes(PARAM_USER, user.get());
            sessionRequestContent.setRequestAttributes(ATTRIBUTE_USER_STATUS_VALUES, UserStatus.values());
        } catch (ServiceException e) {
            logger.error(e);
        }
        page = PagePathManager.getProperty(PagePathProperty.USER_EDIT_PAGE_PROPERTY);
        return page;
    }
}