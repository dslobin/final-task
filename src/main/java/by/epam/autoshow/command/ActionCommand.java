package by.epam.autoshow.command;

import by.epam.autoshow.controller.SessionRequestContent;

/**
 * Basic servlet action command interface.
 * Provides execution of {@link SessionRequestContent} data.
 *
 * @author Daniil Slobin
 */
public interface ActionCommand {
    /**
     * Function for extracting and processing data from a request or session
     *
     * @param sessionRequestContent - data in the request and in the session
     * @return the page at the specified URL
     */
    String execute(SessionRequestContent sessionRequestContent);
}