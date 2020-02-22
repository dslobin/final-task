package by.epam.autoshow.command.impl.car;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.Car;
import by.epam.autoshow.model.UserRole;
import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.util.provider.PagePathProperty;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.CarServiceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GetAllCarsCommand implements ActionCommand {
    private static final String PARAM_CAR_LIST = "carList";
    private static final String ATTRIBUTE_USER_ROLE = "userRole";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        String page = null;
        try {
            CarServiceImpl carService = CarServiceImpl.getInstance();
            List<Car> cars = carService.findAllCars();
            logger.debug("CAR LIST: " + cars);
            content.setRequestAttributes(PARAM_CAR_LIST, cars);
            UserRole userRole = (UserRole) content.getSessionAttributes(ATTRIBUTE_USER_ROLE);
            if (UserRole.ADMIN.equals(userRole)) {
                page = PagePathProvider.getProperty(PagePathProperty.ADMIN_CAR_OVERVIEW_PAGE_PROPERTY);
            } else {
                page = PagePathProvider.getProperty(PagePathProperty.CLIENT_CAR_OVERVIEW_PAGE_PROPERTY);
            }
        } catch (ServiceException e) {
            logger.error(e);
        }
        return page;
    }
}
