package by.epam.autoshow.command.impl.carservice;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.AutoShowService;
import by.epam.autoshow.model.UserRole;
import by.epam.autoshow.service.AutoShowServiceManagement;
import by.epam.autoshow.util.provider.MessagePath;
import by.epam.autoshow.util.provider.MessageProvider;
import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.util.provider.JspPagePath;
import by.epam.autoshow.service.impl.AutoShowServiceManagementImpl;
import by.epam.autoshow.service.ServiceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GetAllServicesCommand implements ActionCommand {
    private static final String PARAM_SERVICE_LIST = "autoShowServiceList";
    private static final String ATTRIBUTE_USER_ROLE = "userRole";
    private static final String ATTRIBUTE_SERVER_ERROR = "serverError";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        String page = null;
        try {
            AutoShowServiceManagement serviceManagement = AutoShowServiceManagementImpl.getInstance();
            List<AutoShowService> services = serviceManagement.findAllServices();
            content.setRequestAttributes(PARAM_SERVICE_LIST, services);
            UserRole userRole = (UserRole) content.getSessionAttributes(ATTRIBUTE_USER_ROLE);
            if (UserRole.ADMIN.equals(userRole)) {
                page = PagePathProvider.getProperty(JspPagePath.ADMIN_SERVICE_OVERVIEW_PAGE_PROPERTY);
            } else {
                page = PagePathProvider.getProperty(JspPagePath.CLIENT_SERVICE_OVERVIEW_PAGE_PROPERTY);
            }
        } catch (ServiceException e) {
            content.setRequestAttributes(ATTRIBUTE_SERVER_ERROR,
                    MessageProvider.getProperty(MessagePath.SERVER_ERROR_PROPERTY));
            page = PagePathProvider.getProperty(JspPagePath.ERROR_PAGE_PROPERTY);
            logger.error(e);
        }
        return page;
    }
}