package by.epam.autoshow.command.impl;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.util.manager.PagePathManager;
import by.epam.autoshow.util.manager.PagePathPropertyProvider;

public class GetLoginPageCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = PagePathManager.getProperty(PagePathPropertyProvider.LOGIN_PAGE_PROPERTY);
        return page;
    }
}