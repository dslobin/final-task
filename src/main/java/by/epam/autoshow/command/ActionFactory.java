package by.epam.autoshow.command;

import by.epam.autoshow.command.impl.EmptyCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.UserRole;
import by.epam.autoshow.util.provider.MessageProperty;

import by.epam.autoshow.util.provider.MessageProvider;

import java.util.Optional;

public class ActionFactory {
    private static final String PARAM_COMMAND = "command";
    private static final String PARAM_USER_ROLE = "userRole";
    private static final String ATTRIBUTE_WRONG_ACTION = "wrongAction";
    private static final String ATTRIBUTE_FORBIDDEN = "forbidden";

    public ActionCommand defineCommand(SessionRequestContent content) {
        ActionCommand actionCommand = new EmptyCommand();
        String action = content.getRequestParameter(PARAM_COMMAND);
        UserRole role = (UserRole) content.getSessionAttributes(PARAM_USER_ROLE);
        StringBuffer message = new StringBuffer();
        if (action == null || action.isBlank()) {
            return actionCommand;
        }
        try {
            CommandType commandType = CommandType.valueOf(action.toUpperCase());
            Optional<UserAuthorityCommand> command = UserAuthorityCommand.fromString(action);
            boolean isUserRoleValid = false;
            if (command.isPresent()) {
                isUserRoleValid = command.get().isValidRole(role.name());
            }
            if (isUserRoleValid) {
                actionCommand = commandType.getCurrentCommand();
            } else {
                content.setRequestAttributes(ATTRIBUTE_FORBIDDEN,
                        MessageProvider.getProperty(MessageProperty.INVALID_USER_ROLE_PROPERTY));
            }
        } catch (IllegalArgumentException e) {
            message.append(MessageProvider.getProperty(MessageProperty.WRONG_ACTION_PROPERTY))
                    .append(" - ").append(action);
            content.setRequestAttributes(ATTRIBUTE_WRONG_ACTION, message.toString());
        }
        return actionCommand;
    }
}
