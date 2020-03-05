package by.epam.autoshow.command.impl;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.command.RouteType;
import by.epam.autoshow.command.Router;
import by.epam.autoshow.util.provider.JspPagePath;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.util.provider.PagePathProvider;

import java.util.Locale;

public class LogoutCommand implements ActionCommand {
    private static final String DEFAULT_LOCALE_LANGUAGE = "ru";
    private static final String DEFAULT_LOCALE_COUNTRY = "RU";

    @Override
    public Router execute(SessionRequestContent content) {
        content.invalidateSession();
        Locale.setDefault(new Locale(DEFAULT_LOCALE_LANGUAGE, DEFAULT_LOCALE_COUNTRY));
        Router router = new Router(PagePathProvider.getProperty(JspPagePath.LOGIN_PAGE_PROPERTY), RouteType.FORWARD);
        return router;
    }
}