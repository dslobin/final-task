package by.epam.autoshow.command.impl;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.command.RouteType;
import by.epam.autoshow.command.Router;
import by.epam.autoshow.util.provider.JspPagePath;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.util.provider.PagePathProvider;

import java.util.Locale;

public class LogoutCommand implements ActionCommand {
    @Override
    public Router execute(SessionRequestContent content) {
        content.invalidateSession();
        Locale.setDefault(new Locale("ru", "RU"));
        String page = PagePathProvider.getProperty(JspPagePath.LOGIN_PAGE_PROPERTY);
        Router router = new Router(page, RouteType.FORWARD);
        return router;
    }
}