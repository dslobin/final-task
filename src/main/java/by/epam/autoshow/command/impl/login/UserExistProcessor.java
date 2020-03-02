package by.epam.autoshow.command.impl.login;

import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.User;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.UserService;
import by.epam.autoshow.util.provider.MessageProperty;
import by.epam.autoshow.util.provider.MessageProvider;
import by.epam.autoshow.util.security.Sha256PasswordEncoder;

import java.util.Optional;

class UserExistProcessor extends AuthenticationProcessor {
    private UserService userService;
    private SessionRequestContent content;
    private static final String ATTRIBUTE_INCORRECT_LOGIN_PASSWORD = "incorrectLoginPassword";
    private static final String ATTRIBUTE_USER_LOGIN = "userLogin";

    UserExistProcessor(UserService userService, SessionRequestContent content) {
        this.userService = userService;
        this.content = content;
    }

    @Override
    public boolean check(User user) throws ServiceException {
        String password = Sha256PasswordEncoder.encode(user.getPassword());
        Optional<User> authorizeUser = userService.authorizeUser(user.getUsername(), password);
        if (authorizeUser.isEmpty()) {
            content.setRequestAttributes(ATTRIBUTE_INCORRECT_LOGIN_PASSWORD,
                    MessageProvider.getProperty(MessageProperty.LOGIN_ERROR_MESSAGE_PROPERTY));
            return false;
        }
        content.setSessionAttributes(ATTRIBUTE_USER_LOGIN, authorizeUser.get().getUsername());
        return checkNext(authorizeUser.get());
    }
}