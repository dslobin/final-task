package by.epam.autoshow.command.impl.user;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.User;
import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.util.provider.PagePathProperty;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.UserServiceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GetAllUsersCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static final String PARAM_USER_LIST = "userList";

    @Override
    public String execute(SessionRequestContent content) {
        String page = null;
        UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();
        try {
            List<User> users = userServiceImpl.findAllUsers();
            logger.debug("USER LIST: " + users);
            content.setRequestAttributes(PARAM_USER_LIST, users);
            page = PagePathProvider.getProperty(PagePathProperty.USER_OVERVIEW_PAGE_PROPERTY);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return page;
    }
}