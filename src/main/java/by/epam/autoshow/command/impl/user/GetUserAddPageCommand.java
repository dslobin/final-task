package by.epam.autoshow.command.impl.user;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.resource.PagePathManager;
import by.epam.autoshow.resource.PagePathPropertyProvider;

public class GetUserAddPageCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = PagePathManager.getProperty(PagePathPropertyProvider.USER_EDIT_FORM_PROPERTY);
        return page;
    }
}