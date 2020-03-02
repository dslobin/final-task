package by.epam.autoshow.command.impl.login;

import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.User;
import by.epam.autoshow.model.UserRole;
import by.epam.autoshow.service.ServiceException;

class UserRoleProcessor extends AuthenticationProcessor {
    private SessionRequestContent content;
    private static final String ATTRIBUTE_USER_ROLE = "userRole";

    UserRoleProcessor(SessionRequestContent content) {
        this.content = content;
    }

    @Override
    public boolean check(User user) throws ServiceException {
        if (UserRole.ADMIN.equals(user.getRole())) {
            content.setSessionAttributes(ATTRIBUTE_USER_ROLE, UserRole.ADMIN);
        }
        if (UserRole.CLIENT.equals(user.getRole())) {
            content.setSessionAttributes(ATTRIBUTE_USER_ROLE, UserRole.CLIENT);
        }
        return checkNext(user);
    }
}
