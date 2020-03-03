package by.epam.autoshow.command.impl;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.util.provider.JspPagePath;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.util.provider.PagePathProvider;

public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) {
        String page = PagePathProvider.getProperty(JspPagePath.LOGIN_PAGE_PROPERTY);
        content.invalidateSession();
        return page;
    }
}