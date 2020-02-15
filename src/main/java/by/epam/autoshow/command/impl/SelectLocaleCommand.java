package by.epam.autoshow.command.impl;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;

public class SelectLocaleCommand implements ActionCommand {
    private static final String PARAM_LANGUAGE = "language";

    @Override
    public String execute(SessionRequestContent content) {
        String page = null;
        String locale = content.getRequestParameter(PARAM_LANGUAGE);
        content.setSessionAttributes(PARAM_LANGUAGE, locale);
        return null;
    }
}