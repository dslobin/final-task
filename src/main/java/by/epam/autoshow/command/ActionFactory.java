package by.epam.autoshow.command;

import by.epam.autoshow.command.impl.EmptyCommand;
import by.epam.autoshow.command.impl.GetErrorPageCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.UserRole;
import by.epam.autoshow.util.provider.MessagePath;
import by.epam.autoshow.util.provider.MessageProvider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class ActionFactory {
    private static final String PARAM_COMMAND = "command";
    private static final String PARAM_USER_ROLE = "userRole";
    private static final String ATTRIBUTE_WRONG_ACTION = "wrongAction";
    private static final String ATTRIBUTE_FORBIDDEN = "forbidden";
    private static final Logger logger = LogManager.getLogger();

    public ActionCommand defineCommand(SessionRequestContent content) {
        ActionCommand actionCommand = new EmptyCommand();
        String action = content.getRequestParameter(PARAM_COMMAND);
        UserRole role = (UserRole) content.getSessionAttributes(PARAM_USER_ROLE);
        StringBuffer message = new StringBuffer();
        if (action == null || action.isBlank()) {
            return actionCommand;
        }
        try {
            logger.debug("User action: " + action);
            CommandType commandType = CommandType.valueOf(action.toUpperCase());
            Optional<UserAuthorityCommand> command = UserAuthorityCommand.fromString(action);
            boolean isUserRoleValid = false;
            if (command.isPresent()) {
                isUserRoleValid = command.get().isValidRole(role.name());
            }
            if (isUserRoleValid) {
                actionCommand = commandType.getCurrentCommand();
            } else {
                actionCommand = new GetErrorPageCommand();
                content.setRequestAttributes(ATTRIBUTE_FORBIDDEN,
                        MessageProvider.getProperty(MessagePath.INVALID_USER_ROLE_PROPERTY));
            }
        } catch (IllegalArgumentException e) {
            actionCommand = new GetErrorPageCommand();
            message.append(MessageProvider.getProperty(MessagePath.WRONG_ACTION_PROPERTY))
                    .append(":").append(action);
            content.setRequestAttributes(ATTRIBUTE_WRONG_ACTION, message.toString());
        }
        return actionCommand;
    }
}