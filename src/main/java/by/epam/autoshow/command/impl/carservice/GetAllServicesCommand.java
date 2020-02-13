package by.epam.autoshow.command.impl.carservice;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.AutoShowService;
import by.epam.autoshow.model.UserRole;
import by.epam.autoshow.util.manager.PagePathManager;
import by.epam.autoshow.util.manager.PagePathProperty;
import by.epam.autoshow.service.impl.AutoShowServiceManagementImpl;
import by.epam.autoshow.service.ServiceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GetAllServicesCommand implements ActionCommand {
    private static final String PARAM_SERVICE_LIST = "autoShowServiceList";
    private static final String ATTRIBUTE_USER_ROLE = "userRole";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = null;
        AutoShowServiceManagementImpl serviceManagement = AutoShowServiceManagementImpl.getInstance();
        try {
            List<AutoShowService> services = serviceManagement.findAllServices();
            logger.debug("SERVICE LIST: " + services);
            sessionRequestContent.setRequestAttributes(PARAM_SERVICE_LIST, services);
            UserRole userRole = (UserRole) sessionRequestContent.getSessionAttributes(ATTRIBUTE_USER_ROLE);
            if (UserRole.ADMIN.equals(userRole)) {
                page = PagePathManager.getProperty(PagePathProperty.ADMIN_SERVICE_OVERVIEW_PAGE_PROPERTY);
            } else {
                page = PagePathManager.getProperty(PagePathProperty.CLIENT_SERVICE_OVERVIEW_PAGE_PROPERTY);
            }
        } catch (ServiceException e) {
            logger.error(e);
        }
        return page;
    }
}