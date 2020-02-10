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

import java.util.Optional;

public class EditCarCommand implements ActionCommand {
    private static final String PARAM_CAR = "car";
    private static final String PARAM_CAR_ID = "carId";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = null;
        CarServiceImpl carService = new CarServiceImpl();
        String carId = sessionRequestContent.getRequestParameter(PARAM_CAR_ID);
        try {
            Optional<Car> car = carService.findCarById(Long.parseLong(carId));
            logger.debug("CAR: " + car);
            sessionRequestContent.setRequestAttributes(PARAM_CAR, car.get());
            page = PagePathManager.getProperty(PagePathPropertyProvider.CAR_EDIT_PAGE_PROPERTY);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return page;
    }
}