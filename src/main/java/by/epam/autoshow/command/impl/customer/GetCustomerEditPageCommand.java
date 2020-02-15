package by.epam.autoshow.command.impl.customer;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.Customer;
import by.epam.autoshow.model.UserStatus;
import by.epam.autoshow.service.CustomerService;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.CustomerServiceImpl;
import by.epam.autoshow.util.manager.PagePathManager;
import by.epam.autoshow.util.manager.PagePathProperty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class GetCustomerEditPageCommand implements ActionCommand {
    private static final String PARAM_CUSTOMER = "user";
    private static final String PARAM_CUSTOMER_ID = "userId";
    private static final String ATTRIBUTE_USER_STATUS = "userStatusArray";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        String page = null;
        CustomerService customerService = CustomerServiceImpl.getInstance();
        String customerId = content.getRequestParameter(PARAM_CUSTOMER_ID);
        try {
            Optional<Customer> customer = customerService.findCustomerById(Long.parseLong(customerId));
            logger.debug("Customer: " + customer);
            content.setRequestAttributes(PARAM_CUSTOMER, customer.get());
            content.setRequestAttributes(ATTRIBUTE_USER_STATUS, UserStatus.values());
            page = PagePathManager.getProperty(PagePathProperty.CUSTOMER_EDIT_PAGE_PROPERTY);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return page;
    }
}