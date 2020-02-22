package by.epam.autoshow.command;

import by.epam.autoshow.command.impl.EmptyCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.util.provider.MessageProvider;

public class ActionFactory {
    private static final String PARAM_COMMAND = "command";
    private static final String ATTRIBUTE_WRONG_ACTION = "wrongAction";
    private static final String WRONG_ACTION_PROPERTY = "label.wrongAction";

    public ActionCommand defineCommand(SessionRequestContent requestContent) {
        ActionCommand actionCommand = new EmptyCommand();
        String action = requestContent.getRequestParameter(PARAM_COMMAND);
        StringBuffer message = new StringBuffer();
        if (action == null || action.isBlank()) {
            return actionCommand;
        }
        try {
            CommandType commandType = CommandType.valueOf(action.toUpperCase());
            actionCommand = commandType.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            message.append(action).append(": ").append(MessageProvider.getProperty(WRONG_ACTION_PROPERTY));
            requestContent.setRequestAttributes(ATTRIBUTE_WRONG_ACTION, message.toString());
        }
        return actionCommand;
    }
}
