package by.epam.autoshow.command.impl.carservice;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.command.RouteType;
import by.epam.autoshow.command.Router;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.AutoShowService;
import by.epam.autoshow.service.AutoShowServiceManagement;
import by.epam.autoshow.util.provider.MessagePath;
import by.epam.autoshow.util.provider.MessageProvider;
import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.util.provider.JspPagePath;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.AutoShowServiceManagementImpl;
import by.epam.autoshow.validation.ServiceDataValidator;
import by.epam.autoshow.validation.ValidatorException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class AddAutoShowServiceCommand implements ActionCommand {
    private static final String PARAM_TITLE = "serviceTitle";
    private static final String PARAM_COST = "serviceCost";
    private static final String PARAM_DESCRIPTION = "serviceDescription";
    private static final String ATTRIBUTE_INVALID_SERVICE = "invalidService";
    private static final String ATTRIBUTE_SERVER_ERROR = "serverError";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(SessionRequestContent content) {
        String serviceTitle = content.getRequestParameter(PARAM_TITLE);
        String cost = content.getRequestParameter(PARAM_COST);
        String description = content.getRequestParameter(PARAM_DESCRIPTION);
        Router router = null;
        ServiceDataValidator serviceValidator = new ServiceDataValidator();
        if (serviceValidator.isTitleValid(serviceTitle) && serviceValidator.isCostValid(cost) &&
                serviceValidator.isDescriptionValid(description)) {
            try {
                AutoShowService autoShowService = new AutoShowService(serviceTitle,
                        BigDecimal.valueOf(Double.parseDouble(cost)), description);
                AutoShowServiceManagement serviceManagement = AutoShowServiceManagementImpl.getInstance();
                serviceManagement.addService(autoShowService);
                router = new Router(JspPagePath.SERVICES_PAGE_URL, RouteType.REDIRECT);
            } catch (ServiceException e) {
                logger.error(e);
                content.setRequestAttributes(ATTRIBUTE_SERVER_ERROR,
                        MessageProvider.getProperty(MessagePath.SERVER_ERROR_PROPERTY));
                router = new Router(PagePathProvider.getProperty(JspPagePath.ERROR_PAGE_PROPERTY), RouteType.FORWARD);
            } catch (ValidatorException e) {
                logger.error(e);
                content.setRequestAttributes(ATTRIBUTE_INVALID_SERVICE,
                        MessageProvider.getProperty(MessagePath.INVALID_SERVICE_UPDATE_PROPERTY));
                router = new Router(PagePathProvider.getProperty(JspPagePath.SERVICE_EDIT_PAGE_PROPERTY),
                        RouteType.FORWARD);
            }
        } else {
            logger.error("Error adding record, auto show service data not valid.");
            content.setRequestAttributes(ATTRIBUTE_INVALID_SERVICE,
                    MessageProvider.getProperty(MessagePath.INVALID_SERVICE_ADDITION_PROPERTY));
            router = new Router(PagePathProvider.getProperty(JspPagePath.SERVICE_EDIT_PAGE_PROPERTY),
                    RouteType.FORWARD);
        }
        return router;
    }
}