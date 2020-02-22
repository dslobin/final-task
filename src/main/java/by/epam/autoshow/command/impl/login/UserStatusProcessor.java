package by.epam.autoshow.command.impl.login;

import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.User;
import by.epam.autoshow.model.UserStatus;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.util.provider.MessageProvider;

public class UserStatusProcessor extends AuthenticationProcessor {
    private SessionRequestContent content;
    private static final String ATTRIBUTE_BLOCKED_USER = "errorBlockedUser";
    private static final String STATUS_ERROR_MESSAGE_PROPERTY = "label.userStatusError";

    UserStatusProcessor(SessionRequestContent content) {
        this.content = content;
    }

    @Override
    public boolean check(User user) throws ServiceException {
        if (UserStatus.BLOCKED.equals(user.getStatus())) {
            content.setRequestAttributes(ATTRIBUTE_BLOCKED_USER, MessageProvider.getProperty(STATUS_ERROR_MESSAGE_PROPERTY));
            return false;
        }
        return checkNext(user);
    }
}
