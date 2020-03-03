package by.epam.autoshow.command;

import by.epam.autoshow.controller.SessionRequestContent;

/**
 * Servlet action command interface.
 * Provides execution of {@link SessionRequestContent} data.
 *
 * @author Daniil Slobin
 */
public interface ActionCommand {
    /**
     * Function for extracting and processing data from a request or session
     *
     * @param content data in the request and in the session
     * @return page path
     */
    Router execute(SessionRequestContent content);
}