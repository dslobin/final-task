package by.epam.autoshow.command.impl.customer;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.command.RouteType;
import by.epam.autoshow.command.Router;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.util.provider.JspPagePath;

public class GetCustomerAddPageCommand implements ActionCommand {
    @Override
    public Router execute(SessionRequestContent content) {
        Router router = new Router(PagePathProvider.getProperty(JspPagePath.CUSTOMER_EDIT_PAGE_PROPERTY),
                RouteType.FORWARD);
        return router;
    }
}