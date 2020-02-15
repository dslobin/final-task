package by.epam.autoshow.command.impl.carservice;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.AutoShowService;
import by.epam.autoshow.util.manager.PagePathManager;
import by.epam.autoshow.util.manager.PagePathProperty;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.AutoShowServiceManagementImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class EditAutoShowServiceCommand implements ActionCommand {
    private static final String PARAM_SERVICE_ID = "serviceId";
    private static final String PARAM_TITLE = "serviceTitle";
    private static final String PARAM_COST = "serviceCost";
    private static final String PARAM_DESCRIPTION = "serviceDescription";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        String page = null;
        String serviceId = content.getRequestParameter(PARAM_SERVICE_ID);
        String serviceTitle = content.getRequestParameter(PARAM_TITLE);
        String cost = content.getRequestParameter(PARAM_COST);
        String description = content.getRequestParameter(PARAM_DESCRIPTION);
        try {
            AutoShowService autoShowService = new AutoShowService();
            autoShowService.setServiceId(Long.parseLong(serviceId));
            autoShowService.setTitle(serviceTitle);
            autoShowService.setDescription(description);
            autoShowService.setCost(BigDecimal.valueOf(Double.parseDouble(cost)));
            AutoShowServiceManagementImpl serviceManagement = AutoShowServiceManagementImpl.getInstance();
            serviceManagement.updateService(autoShowService);
            page = PagePathManager.getProperty(PagePathProperty.SERVICE_EDIT_PAGE_PROPERTY);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return page;
    }
}
