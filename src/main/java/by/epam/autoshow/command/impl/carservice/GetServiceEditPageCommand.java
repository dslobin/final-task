package by.epam.autoshow.command.impl.carservice;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.AutoShowService;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.AutoShowServiceManagementImpl;
import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.util.provider.JspPagePath;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class GetServiceEditPageCommand implements ActionCommand {
    private static final String PARAM_AUTO_SHOW_SERVICE = "autoShowService";
    private static final String PARAM_SERVICE_ID = "serviceId";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        String page = null;
        AutoShowServiceManagementImpl serviceManagement = AutoShowServiceManagementImpl.getInstance();
        String serviceId = content.getRequestParameter(PARAM_SERVICE_ID);
        try {
            Optional<AutoShowService> autoShowService = serviceManagement.findServiceById(Long.parseLong(serviceId));
            logger.debug("Auto show service: " + autoShowService);
            content.setRequestAttributes(PARAM_AUTO_SHOW_SERVICE, autoShowService.get());
            page = PagePathProvider.getProperty(JspPagePath.SERVICE_EDIT_PAGE_PROPERTY);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return page;
    }
}