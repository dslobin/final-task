package by.epam.autoshow.listener;

import by.epam.autoshow.model.BodyType;
import by.epam.autoshow.model.FuelType;
import by.epam.autoshow.model.SaleStatus;
import by.epam.autoshow.model.UserStatus;
import by.epam.autoshow.service.CarService;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.CarServiceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {
    private static final String ATTRIBUTE_CAR_FUEL_TYPE_VALUES = "fuelTypeList";
    private static final String ATTRIBUTE_CAR_BODY_TYPE_VALUES = "bodyTypeList";
    private static final String ATTRIBUTE_SALE_STATUS_VALUES = "saleStatusList";
    private static final String ATTRIBUTE_CAR_COLOR_VALUES = "colorList";
    private static final String ATTRIBUTE_USER_STATUS_VALUES = "userStatuses";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        CarService carService = CarServiceImpl.getInstance();
        try {
            context.setAttribute(ATTRIBUTE_CAR_COLOR_VALUES, carService.findAllColors());
        } catch (ServiceException e) {
            logger.error("Impossible to fill in the color select field with car colors", e);
        }
        context.setAttribute(ATTRIBUTE_CAR_BODY_TYPE_VALUES, BodyType.values());
        context.setAttribute(ATTRIBUTE_CAR_FUEL_TYPE_VALUES, FuelType.values());
        context.setAttribute(ATTRIBUTE_SALE_STATUS_VALUES, SaleStatus.values());
        context.setAttribute(ATTRIBUTE_USER_STATUS_VALUES, UserStatus.values());
        logger.debug("Select fields initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        context.removeAttribute(ATTRIBUTE_CAR_BODY_TYPE_VALUES);
        context.removeAttribute(ATTRIBUTE_CAR_FUEL_TYPE_VALUES);
        context.removeAttribute(ATTRIBUTE_SALE_STATUS_VALUES);
        context.removeAttribute(ATTRIBUTE_USER_STATUS_VALUES);
        logger.debug("Select fields data destroyed");
    }
}
