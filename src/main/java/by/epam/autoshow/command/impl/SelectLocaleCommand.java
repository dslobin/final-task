package by.epam.autoshow.command.impl;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;

public class SelectLocaleCommand implements ActionCommand {
    private static final String PARAM_LANGUAGE = "language";

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = null;
        String locale = sessionRequestContent.getRequestParameter(PARAM_LANGUAGE);
        sessionRequestContent.setSessionAttributes(PARAM_LANGUAGE, locale);
        return null;
    }
}