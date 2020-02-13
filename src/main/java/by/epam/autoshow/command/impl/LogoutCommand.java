package by.epam.autoshow.command.impl;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.util.manager.PagePathProperty;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.util.manager.PagePathManager;

public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = PagePathManager.getProperty(PagePathProperty.LOGIN_PAGE_PROPERTY);
        sessionRequestContent.invalidateSession();
        return page;
    }
}