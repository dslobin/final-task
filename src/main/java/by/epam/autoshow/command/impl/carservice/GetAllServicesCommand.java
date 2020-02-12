package by.epam.autoshow.command.impl.carservice;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.AutoShowService;
import by.epam.autoshow.util.manager.PagePathManager;
import by.epam.autoshow.util.manager.PagePathPropertyProvider;
import by.epam.autoshow.service.impl.AutoShowServiceManagementImpl;
import by.epam.autoshow.service.ServiceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GetAllServicesCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static final String PARAM_SERVICE_LIST = "autoShowServiceList";

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = null;
        AutoShowServiceManagementImpl serviceManagement = AutoShowServiceManagementImpl.getInstance();
        try {
            List<AutoShowService> services = serviceManagement.findAllServices();
            logger.debug("SERVICE LIST: " + services);
            sessionRequestContent.setRequestAttributes(PARAM_SERVICE_LIST, services);
            page = PagePathManager.getProperty(PagePathPropertyProvider.SERVICE_OVERVIEW_PAGE_PROPERTY);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return page;
    }
}