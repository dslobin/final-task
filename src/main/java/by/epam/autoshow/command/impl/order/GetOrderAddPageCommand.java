package by.epam.autoshow.command.impl.order;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.util.manager.PagePathManager;
import by.epam.autoshow.util.manager.PagePathProperty;

public class GetOrderAddPageCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) {
        String page = PagePathManager.getProperty(PagePathProperty.ORDER_EDIT_PAGE_PROPERTY);
        return page;
    }
}