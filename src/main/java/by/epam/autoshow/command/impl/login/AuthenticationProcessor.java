package by.epam.autoshow.command.impl.login;

import by.epam.autoshow.model.User;
import by.epam.autoshow.service.ServiceException;

public abstract class AuthenticationProcessor {
    private AuthenticationProcessor next;

    public AuthenticationProcessor linkWith(AuthenticationProcessor next) {
        this.next = next;
        return next;
    }

    public abstract boolean check(User user) throws ServiceException;

    protected boolean checkNext(User user) throws ServiceException {
        if (next == null) {
            return true;
        }
        return next.check(user);
    }
}