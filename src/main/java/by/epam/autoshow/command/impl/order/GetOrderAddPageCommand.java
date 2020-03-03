package by.epam.autoshow.command.impl.order;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.command.RouteType;
import by.epam.autoshow.command.Router;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.*;
import by.epam.autoshow.service.AutoShowServiceManagement;
import by.epam.autoshow.service.CustomerService;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.AutoShowServiceManagementImpl;
import by.epam.autoshow.service.impl.CustomerServiceImpl;
import by.epam.autoshow.util.provider.MessagePath;
import by.epam.autoshow.util.provider.MessageProvider;
import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.util.provider.JspPagePath;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class GetOrderAddPageCommand implements ActionCommand {
    private static final String ATTRIBUTE_CUSTOMER = "customer";
    private static final String ATTRIBUTE_USER_LOGIN = "userLogin";
    private static final String PARAM_SERVICE_ID = "serviceId";
    private static final String ATTRIBUTE_SERVICE = "service";
    private static final String ATTRIBUTE_SERVER_ERROR = "serverError";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(SessionRequestContent content) {
        String username = (String) content.getSessionAttributes(ATTRIBUTE_USER_LOGIN);
        String serviceId = content.getRequestParameter(PARAM_SERVICE_ID);
        String page = null;
        try {
            CustomerService customerService = CustomerServiceImpl.getInstance();
            AutoShowServiceManagement serviceManagement = AutoShowServiceManagementImpl.getInstance();
            Optional<AutoShowService> service = serviceManagement.findServiceById(Long.parseLong(serviceId));
            Optional<Customer> customer = customerService.findCustomerByLogin(username);
            content.setRequestAttributes(ATTRIBUTE_CUSTOMER, customer.get());
            content.setRequestAttributes(ATTRIBUTE_SERVICE, service.get());
            page = PagePathProvider.getProperty(JspPagePath.ORDER_EDIT_PAGE_PROPERTY);
        } catch (ServiceException e) {
            logger.error(e);
            content.setRequestAttributes(ATTRIBUTE_SERVER_ERROR,
                    MessageProvider.getProperty(MessagePath.SERVER_ERROR_PROPERTY));
            page = PagePathProvider.getProperty(JspPagePath.ERROR_PAGE_PROPERTY);
        }
        Router router = new Router(page, RouteType.FORWARD);
        return router;
    }
}