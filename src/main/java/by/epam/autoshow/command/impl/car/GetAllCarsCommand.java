package by.epam.autoshow.command.impl.car;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.car.Car;
import by.epam.autoshow.util.manager.PagePathManager;
import by.epam.autoshow.util.manager.PagePathPropertyProvider;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.CarServiceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GetAllCarsCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static final String PARAM_CAR_LIST = "carList";

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = null;
        CarServiceImpl userServiceImpl = new CarServiceImpl();
        try {
            List<Car> cars = userServiceImpl.findAllCars();
            logger.debug("CAR LIST: " + cars);
            sessionRequestContent.setRequestAttributes(PARAM_CAR_LIST, cars);
            page = PagePathManager.getProperty(PagePathPropertyProvider.CAR_OVERVIEW_PAGE_PROPERTY);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return page;
    }
}
