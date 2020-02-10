package by.epam.autoshow.command.impl.customer;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;

import by.epam.autoshow.model.customer.Customer;
import by.epam.autoshow.resource.PagePathManager;
import by.epam.autoshow.resource.PagePathPropertyProvider;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.CustomerServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class EditCustomerCommand implements ActionCommand {
    private static final String PARAM_CUSTOMER = "customer";
    private static final String PARAM_CUSTOMER_ID = "customerId";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = null;
        CustomerServiceImpl customerService = new CustomerServiceImpl();
        String customerId = sessionRequestContent.getRequestParameter(PARAM_CUSTOMER_ID);
        try {
            Optional<Customer> customer = customerService.findCustomerById(Long.parseLong(customerId));
            logger.debug("Customer: " + customer);
            sessionRequestContent.setRequestAttributes(PARAM_CUSTOMER, customer.get());
            page = PagePathManager.getProperty(PagePathPropertyProvider.USER_EDIT_FORM_PROPERTY);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return page;
    }
}
