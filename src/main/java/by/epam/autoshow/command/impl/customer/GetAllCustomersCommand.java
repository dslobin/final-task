package by.epam.autoshow.command.impl.customer;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.Customer;
import by.epam.autoshow.service.CustomerService;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.CustomerServiceImpl;
import by.epam.autoshow.util.manager.PagePathManager;
import by.epam.autoshow.util.manager.PagePathProperty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GetAllCustomersCommand implements ActionCommand {
    private static final String PARAM_CUSTOMER_LIST = "customerList";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = null;
        try {
            CustomerService customerService = CustomerServiceImpl.getInstance();
            List<Customer> customers = customerService.findAllCustomers();
            logger.debug("CUSTOMER LIST: " + customers);
            sessionRequestContent.setRequestAttributes(PARAM_CUSTOMER_LIST, customers);
            page = PagePathManager.getProperty(PagePathProperty.CUSTOMER_OVERVIEW_PAGE_PROPERTY);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return page;
    }
}