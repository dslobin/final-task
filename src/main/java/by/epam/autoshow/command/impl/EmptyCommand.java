package by.epam.autoshow.command.impl;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.util.provider.PagePathProperty;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.util.provider.PagePathProvider;

public class EmptyCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) {
        String page = PagePathProvider.getProperty(PagePathProperty.HOME_PAGE_PROPERTY);
        return page;
    }
}