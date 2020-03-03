package by.epam.autoshow.command.impl;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.util.provider.JspPagePath;
import by.epam.autoshow.util.provider.PagePathProvider;

public class GetErrorPageCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) {
        String page = PagePathProvider.getProperty(JspPagePath.ERROR_PAGE_PROPERTY);
        return page;
    }
}