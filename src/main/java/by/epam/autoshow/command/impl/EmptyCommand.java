package by.epam.autoshow.command.impl;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.util.manager.PagePathProperty;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.util.manager.PagePathManager;

public class EmptyCommand implements ActionCommand {

    @Override
    public String execute(SessionRequestContent content) {
        String page = PagePathManager.getProperty(PagePathProperty.ERROR_PAGE_PROPERTY);
        return page;
    }
}
