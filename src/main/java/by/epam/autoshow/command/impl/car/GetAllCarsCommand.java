package by.epam.autoshow.command.impl.car;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.Car;
import by.epam.autoshow.model.UserRole;
import by.epam.autoshow.util.provider.MessageProperty;
import by.epam.autoshow.util.provider.MessageProvider;
import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.util.provider.PagePathProperty;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.CarServiceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class GetAllCarsCommand implements ActionCommand {
    private static final String PARAM_CAR_LIST = "carList";
    private static final String ATTRIBUTE_USER_ROLE = "userRole";
    private static final String ATTRIBUTE_SERVER_ERROR = "serverError";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        String page = null;
        try {
            UserRole userRole = (UserRole) content.getSessionAttributes(ATTRIBUTE_USER_ROLE);
            CarServiceImpl carService = CarServiceImpl.getInstance();
            List<Car> cars = new ArrayList<>();
            if (UserRole.ADMIN.equals(userRole)) {
                cars = carService.findAllCars();
                page = PagePathProvider.getProperty(PagePathProperty.ADMIN_CAR_OVERVIEW_PAGE_PROPERTY);
            } else {
                cars = carService.findCarsForSale();
                page = PagePathProvider.getProperty(PagePathProperty.CLIENT_CAR_OVERVIEW_PAGE_PROPERTY);
            }
            content.setRequestAttributes(PARAM_CAR_LIST, cars);
        } catch (ServiceException e) {
            content.setRequestAttributes(ATTRIBUTE_SERVER_ERROR,
                    MessageProvider.getProperty(MessageProperty.SERVER_ERROR_PROPERTY));
            page = PagePathProvider.getProperty(PagePathProperty.ERROR_PAGE_PROPERTY);
            logger.error(e);
        }
        return page;
    }
}
