package by.epam.autoshow.command.impl.carservice;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.command.RouteType;
import by.epam.autoshow.command.Router;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.AutoShowService;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.AutoShowServiceManagementImpl;
import by.epam.autoshow.util.provider.MessagePath;
import by.epam.autoshow.util.provider.MessageProvider;
import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.util.provider.JspPagePath;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class GetServiceEditPageCommand implements ActionCommand {
    private static final String PARAM_AUTO_SHOW_SERVICE = "autoShowService";
    private static final String PARAM_SERVICE_ID = "serviceId";
    private static final String ATTRIBUTE_SERVER_ERROR = "serverError";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(SessionRequestContent content) {
        AutoShowServiceManagementImpl serviceManagement = AutoShowServiceManagementImpl.getInstance();
        String serviceId = content.getRequestParameter(PARAM_SERVICE_ID);
        Router router = null;
        try {
            Optional<AutoShowService> autoShowService = serviceManagement.findServiceById(Long.parseLong(serviceId));
            content.setRequestAttributes(PARAM_AUTO_SHOW_SERVICE, autoShowService.get());
            router = new Router(PagePathProvider.getProperty(JspPagePath.SERVICE_EDIT_PAGE_PROPERTY),
                    RouteType.FORWARD);
        } catch (ServiceException e) {
            logger.error(e);
            content.setRequestAttributes(ATTRIBUTE_SERVER_ERROR,
                    MessageProvider.getProperty(MessagePath.SERVER_ERROR_PROPERTY));
            router = new Router(PagePathProvider.getProperty(JspPagePath.ERROR_PAGE_PROPERTY), RouteType.FORWARD);
        }
        return router;
    }
}