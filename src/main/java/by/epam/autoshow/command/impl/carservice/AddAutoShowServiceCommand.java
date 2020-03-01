package by.epam.autoshow.command.impl.carservice;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.AutoShowService;
import by.epam.autoshow.service.AutoShowServiceManagement;
import by.epam.autoshow.util.provider.MessageProperty;
import by.epam.autoshow.util.provider.MessageProvider;
import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.util.provider.PagePathProperty;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.AutoShowServiceManagementImpl;
import by.epam.autoshow.validation.ValidatorException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class AddAutoShowServiceCommand implements ActionCommand {
    private static final String PARAM_TITLE = "serviceTitle";
    private static final String PARAM_COST = "serviceCost";
    private static final String PARAM_DESCRIPTION = "serviceDescription";
    private static final String ATTRIBUTE_INVALID_SERVICE = "invalidService";
    private static final String ATTRIBUTE_SERVICE_CHANGED = "successfulServiceChange";
    private static final String ATTRIBUTE_SERVER_ERROR = "serverError";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        String serviceTitle = content.getRequestParameter(PARAM_TITLE);
        String cost = content.getRequestParameter(PARAM_COST);
        String description = content.getRequestParameter(PARAM_DESCRIPTION);
        String page = PagePathProvider.getProperty(PagePathProperty.SERVICE_EDIT_PAGE_PROPERTY);
        try {
            AutoShowService autoShowService = new AutoShowService();
            autoShowService.setTitle(serviceTitle);
            autoShowService.setDescription(description);
            autoShowService.setCost(BigDecimal.valueOf(Double.parseDouble(cost)));
            AutoShowServiceManagement serviceManagement = AutoShowServiceManagementImpl.getInstance();
            serviceManagement.addService(autoShowService);
            content.setRequestAttributes(ATTRIBUTE_SERVICE_CHANGED,
                    MessageProvider.getProperty(MessageProperty.SERVICE_SUCCESSFUL_ADDITION_PROPERTY));
        } catch (ServiceException e) {
            logger.error(e);
            content.setRequestAttributes(ATTRIBUTE_SERVER_ERROR,
                    MessageProvider.getProperty(MessageProperty.SERVER_ERROR_PROPERTY));
            page = PagePathProvider.getProperty(PagePathProperty.ERROR_PAGE_PROPERTY);
        } catch (ValidatorException e) {
            content.setRequestAttributes(ATTRIBUTE_INVALID_SERVICE,
                    MessageProvider.getProperty(MessageProperty.INVALID_SERVICE_ADDITION_PROPERTY));
            logger.error(e);
        }
        return page;
    }
}