package by.epam.autoshow.command.impl;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.UserRole;
import by.epam.autoshow.util.manager.PagePathManager;
import by.epam.autoshow.util.manager.PagePathProperty;

public class GetProfilePageCommand implements ActionCommand {
    private static final String USER_ROLE_ATTRIBUTE = "userRole";

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        UserRole userRole = (UserRole) sessionRequestContent.getSessionAttributes(USER_ROLE_ATTRIBUTE);
        String page = null;
        if (UserRole.ADMIN.equals(userRole)) {
            page = PagePathManager.getProperty(PagePathProperty.PROFILE_PAGE_PROPERTY);
        } else {
            page = PagePathManager.getProperty(PagePathProperty.CLIENT_PROFILE_PAGE_PROPERTY);
        }
        return page;
    }
}
