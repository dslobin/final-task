package by.epam.autoshow.command.impl.user;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.User;
import by.epam.autoshow.service.UserService;
import by.epam.autoshow.util.provider.MessageProperty;
import by.epam.autoshow.util.provider.MessageProvider;
import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.util.provider.PagePathProperty;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.UserServiceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GetAllUsersCommand implements ActionCommand {
    private static final String PARAM_USER_LIST = "userList";
    private static final String ATTRIBUTE_SERVER_ERROR = "serverError";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        String page = null;
        try {
            UserService userService = UserServiceImpl.getInstance();
            List<User> users = userService.findAllUsers();
            content.setRequestAttributes(PARAM_USER_LIST, users);
            page = PagePathProvider.getProperty(PagePathProperty.USER_OVERVIEW_PAGE_PROPERTY);
        } catch (ServiceException e) {
            logger.error(e);
            content.setRequestAttributes(ATTRIBUTE_SERVER_ERROR,
                    MessageProvider.getProperty(MessageProperty.SERVER_ERROR_PROPERTY));
            page = PagePathProvider.getProperty(PagePathProperty.ERROR_PAGE_PROPERTY);
        }
        return page;
    }
}