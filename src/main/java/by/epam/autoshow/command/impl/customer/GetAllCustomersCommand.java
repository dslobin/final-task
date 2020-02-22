package by.epam.autoshow.command.impl.customer;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.Customer;
import by.epam.autoshow.service.CustomerService;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.CustomerServiceImpl;
import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.util.provider.PagePathProperty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class GetAllCustomersCommand implements ActionCommand {
    private static final String PARAM_CUSTOMER_MAP = "customerMap";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        String page = null;
        try {
            CustomerService customerService = CustomerServiceImpl.getInstance();
            Map<String, Customer> customers = customerService.findCustomerUserNames();
            logger.debug("CUSTOMER MAP: " + customers);
            content.setRequestAttributes(PARAM_CUSTOMER_MAP, customers);
            page = PagePathProvider.getProperty(PagePathProperty.CUSTOMER_OVERVIEW_PAGE_PROPERTY);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return page;
    }
}