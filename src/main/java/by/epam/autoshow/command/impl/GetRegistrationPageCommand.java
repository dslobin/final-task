package by.epam.autoshow.command.impl;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.command.RouteType;
import by.epam.autoshow.command.Router;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.util.provider.JspPagePath;

public class GetRegistrationPageCommand implements ActionCommand {
    @Override
    public Router execute(SessionRequestContent content) {
        Router router = new Router(PagePathProvider.getProperty(JspPagePath.REGISTRATION_PAGE_PROPERTY),
                RouteType.FORWARD);
        return router;
    }
}