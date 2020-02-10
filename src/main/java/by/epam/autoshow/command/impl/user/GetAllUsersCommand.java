package by.epam.autoshow.command.impl.user;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.user.User;
import by.epam.autoshow.resource.PagePathManager;
import by.epam.autoshow.resource.PagePathPropertyProvider;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.UserServiceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GetAllUsersCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static final String PARAM_USER_LIST = "userList";

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = null;
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        try {
            List<User> users = userServiceImpl.findAllUsers();
            logger.debug("USER LIST: " + users);
            sessionRequestContent.setRequestAttributes(PARAM_USER_LIST, users);
            page = PagePathManager.getProperty(PagePathPropertyProvider.USER_OVERVIEW_PAGE_PROPERTY);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return page;
    }
}