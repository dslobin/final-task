package by.epam.autoshow.command.impl;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.util.manager.PagePathManager;
import by.epam.autoshow.util.manager.PagePathProperty;

public class GetLoginPageCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) {
        String page = PagePathManager.getProperty(PagePathProperty.LOGIN_PAGE_PROPERTY);
        return page;
    }
}