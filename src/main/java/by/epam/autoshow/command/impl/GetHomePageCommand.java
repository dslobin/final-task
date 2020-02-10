package by.epam.autoshow.command.impl;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.resource.PagePathManager;
import by.epam.autoshow.resource.PagePathPropertyProvider;

public class GetHomePageCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = PagePathManager.getProperty(PagePathPropertyProvider.HOME_PAGE_PROPERTY);
        return page;
    }
}