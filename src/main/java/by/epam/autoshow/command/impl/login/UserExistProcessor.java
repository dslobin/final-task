package by.epam.autoshow.command.impl.login;

import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.User;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.UserService;
import by.epam.autoshow.util.provider.MessageProperty;
import by.epam.autoshow.util.provider.MessageProvider;

import java.util.Optional;

public class UserExistProcessor extends AuthenticationProcessor {
    private UserService userService;
    private SessionRequestContent content;
    private static final String ATTRIBUTE_ERROR_MESSAGE = "errorLoginPasswordMessage";
    private static final String ATTRIBUTE_USER_LOGIN = "userLogin";

    UserExistProcessor(UserService userService, SessionRequestContent content) {
        this.userService = userService;
        this.content = content;
    }

    @Override
    public boolean check(User user) throws ServiceException {
        Optional<User> authorizeUser = userService.authorizeUser(user.getUsername(), user.getPassword());
        if (authorizeUser.isEmpty()) {
            content.setRequestAttributes(ATTRIBUTE_ERROR_MESSAGE,
                    MessageProvider.getProperty(MessageProperty.LOGIN_ERROR_MESSAGE_PROPERTY));
            return false;
        }
        content.setSessionAttributes(ATTRIBUTE_USER_LOGIN, authorizeUser.get().getUsername());
        return checkNext(authorizeUser.get());
    }
}