package by.epam.autoshow.command.impl.order;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.AutoShowService;
import by.epam.autoshow.model.Customer;
import by.epam.autoshow.model.Order;
import by.epam.autoshow.model.OrderStatus;
import by.epam.autoshow.service.AutoShowServiceManagement;
import by.epam.autoshow.service.CustomerService;
import by.epam.autoshow.service.OrderService;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.AutoShowServiceManagementImpl;
import by.epam.autoshow.service.impl.CustomerServiceImpl;
import by.epam.autoshow.service.impl.OrderServiceImpl;
import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.util.provider.PagePathProperty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

public class CreateOrderCommand implements ActionCommand {
    private static final String ATTRIBUTE_USER_LOGIN = "userLogin";
    private static final String PARAM_SERVICE_ID = "serviceId";
    private static final String PARAM_SERVICE_DATE = "serviceDate";
    private static final String PARAM_SERVICE_TIME = "serviceTime";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        String username = (String) content.getSessionAttributes(ATTRIBUTE_USER_LOGIN);
        String serviceId = content.getRequestParameter(PARAM_SERVICE_ID);
        String serviceDate = content.getRequestParameter(PARAM_SERVICE_DATE);
        String serviceTime = content.getRequestParameter(PARAM_SERVICE_TIME);
        try {
            CustomerService customerService = CustomerServiceImpl.getInstance();
            AutoShowServiceManagement serviceManagement = AutoShowServiceManagementImpl.getInstance();
            OrderService orderService = OrderServiceImpl.getInstance();
            Optional<AutoShowService> service = serviceManagement.findServiceById(Long.parseLong(serviceId));
            Optional<Customer> customer = customerService.findCustomerByLogin(username);
            Order order = new Order();
            order.setService(service.get());
            order.setPrice(service.get().getCost());
            order.setCustomer(customer.get());
            LocalDate localDate = LocalDate.parse(serviceDate);
            LocalTime localTime = LocalTime.parse(serviceTime);
            LocalDateTime orderDate = LocalDateTime.of(localDate.getYear(), localDate.getMonth(),
                    localDate.getDayOfMonth(), localTime.getHour(), localTime.getMinute());
            order.setServiceTime(orderDate);
            order.setStatus(OrderStatus.NEW);
            orderService.addOrder(order);
        } catch (ServiceException e) {
            logger.error(e);
        }
        String page = PagePathProvider.getProperty(PagePathProperty.PROFILE_PAGE_PROPERTY);
        return page;
    }
}