package by.epam.autoshow.command.impl.order;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.*;
import by.epam.autoshow.service.AutoShowServiceManagement;
import by.epam.autoshow.service.CustomerService;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.AutoShowServiceManagementImpl;
import by.epam.autoshow.service.impl.CustomerServiceImpl;
import by.epam.autoshow.util.manager.PagePathManager;
import by.epam.autoshow.util.manager.PagePathProperty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class GetOrderAddPageCommand implements ActionCommand {
    private static final String ATTRIBUTE_CUSTOMER = "customer";
    private static final String ATTRIBUTE_USER_LOGIN = "userLogin";
    private static final String PARAM_SERVICE_ID = "serviceId";
    private static final String ATTRIBUTE_SERVICE = "service";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        String username = (String) content.getSessionAttributes(ATTRIBUTE_USER_LOGIN);
        String serviceId = content.getRequestParameter(PARAM_SERVICE_ID);
        try {
            CustomerService customerService = CustomerServiceImpl.getInstance();
            AutoShowServiceManagement serviceManagement = AutoShowServiceManagementImpl.getInstance();
            Optional<AutoShowService> service = serviceManagement.findServiceById(Long.parseLong(serviceId));
            Optional<Customer> customer = customerService.findCustomerByLogin(username);
            logger.debug("CUSTOMER: " + customer.get());
            logger.debug("SERVICE: " + service.get());
            content.setRequestAttributes(ATTRIBUTE_CUSTOMER, customer.get());
            content.setRequestAttributes(ATTRIBUTE_SERVICE, service.get());
        } catch (ServiceException e) {
            logger.error(e);
        }
        String page = PagePathManager.getProperty(PagePathProperty.ORDER_EDIT_PAGE_PROPERTY);
        return page;
    }
}