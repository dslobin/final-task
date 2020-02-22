package by.epam.autoshow.command.impl.customer;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.UserStatus;
import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.util.provider.PagePathProperty;

public class GetCustomerAddPageCommand implements ActionCommand {
    private static final String ATTRIBUTE_USER_STATUS = "userStatusArray";

    @Override
    public String execute(SessionRequestContent content) {
        content.setRequestAttributes(ATTRIBUTE_USER_STATUS, UserStatus.values());
        String page = PagePathProvider.getProperty(PagePathProperty.CUSTOMER_EDIT_PAGE_PROPERTY);
        return page;
    }
}