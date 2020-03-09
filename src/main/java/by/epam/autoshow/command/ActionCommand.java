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
     * @return an object that contains the path to the page
     * and the type of transition {@code RouteType.FORWARD or RouteType.REDIRECT}
     * @see Router
     * @see RouteType
     */
    Router execute(SessionRequestContent content);
}