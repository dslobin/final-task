package by.epam.autoshow.command.impl.login;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.command.RouteType;
import by.epam.autoshow.command.Router;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.util.provider.JspPagePath;

public class GetLoginPageCommand implements ActionCommand {
    @Override
    public Router execute(SessionRequestContent content) {
        String page = PagePathProvider.getProperty(JspPagePath.LOGIN_PAGE_PROPERTY);
        Router router = new Router(page, RouteType.FORWARD);
        return router;
    }
}