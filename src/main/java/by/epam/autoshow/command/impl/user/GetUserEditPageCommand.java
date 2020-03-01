package by.epam.autoshow.command.impl.user;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.User;
import by.epam.autoshow.model.UserStatus;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.UserService;
import by.epam.autoshow.service.impl.UserServiceImpl;
import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.util.provider.PagePathProperty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class GetUserEditPageCommand implements ActionCommand {
    private static final String PARAM_USER = "user";
    private static final String PARAM_USER_ID = "userId";
    private static final String ATTRIBUTE_USER_STATUS_VALUES = "userStatusArray";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        String page = null;
        String userId = content.getRequestParameter(PARAM_USER_ID);
        try {
            UserService userService = UserServiceImpl.getInstance();
            Optional<User> user = userService.findUserById(Long.parseLong(userId));
            content.setRequestAttributes(PARAM_USER, user.get());
            content.setRequestAttributes(ATTRIBUTE_USER_STATUS_VALUES, UserStatus.values());
        } catch (ServiceException e) {
            logger.error(e);
        }
        page = PagePathProvider.getProperty(PagePathProperty.USER_EDIT_PAGE_PROPERTY);
        return page;
    }
}