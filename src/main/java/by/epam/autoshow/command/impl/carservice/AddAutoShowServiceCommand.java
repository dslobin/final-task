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

public class AddAutoShowServiceCommand implements ActionCommand {
    private static final String PARAM_TITLE = "title";
    private static final String PARAM_COST = "cost";
    private static final String PARAM_DESCRIPTION = "description";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = null;
        String serviceTitle = sessionRequestContent.getRequestParameter(PARAM_TITLE);
        String cost = sessionRequestContent.getRequestParameter(PARAM_COST);
        String description = sessionRequestContent.getRequestParameter(PARAM_DESCRIPTION);
        try {
            AutoShowService autoShowService = new AutoShowService();
            autoShowService.setTitle(serviceTitle);
            autoShowService.setDescription(description);
            autoShowService.setCost(BigDecimal.valueOf(Double.parseDouble(cost)));
            AutoShowServiceManagementImpl serviceManagement = AutoShowServiceManagementImpl.getInstance();
            serviceManagement.addService(autoShowService);
            page = PagePathManager.getProperty(PagePathProperty.SERVICE_EDIT_FORM_PROPERTY);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return page;
    }
}
