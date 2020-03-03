package by.epam.autoshow.command.impl.login;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.util.provider.JspPagePath;

public class GetLoginPageCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) {
        String page = PagePathProvider.getProperty(JspPagePath.LOGIN_PAGE_PROPERTY);
        return page;
    }
}