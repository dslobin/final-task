package by.epam.autoshow.command.impl;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;

public class ChangeLocaleCommand implements ActionCommand {
    private static final String PARAM_LANGUAGE = "language";
    private static final String CURRENT_PAGE_PARAMETER = "currentPage";

    @Override
    public String execute(SessionRequestContent content) {
        String locale = content.getRequestParameter(PARAM_LANGUAGE);
        String page = content.getRequestParameter(CURRENT_PAGE_PARAMETER);
        content.setSessionAttributes(PARAM_LANGUAGE, locale);
        return page;
    }
}