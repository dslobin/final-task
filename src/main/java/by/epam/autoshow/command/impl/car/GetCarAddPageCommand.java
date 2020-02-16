package by.epam.autoshow.command.impl.car;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.util.manager.PagePathManager;
import by.epam.autoshow.util.manager.PagePathProperty;

public class GetCarAddPageCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) {
        String page = PagePathManager.getProperty(PagePathProperty.CAR_EDIT_PAGE_PROPERTY);
        return page;
    }
}