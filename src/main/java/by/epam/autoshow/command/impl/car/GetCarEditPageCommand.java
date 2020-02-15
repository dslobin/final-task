package by.epam.autoshow.command.impl.car;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.Car;
import by.epam.autoshow.service.CarService;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.CarServiceImpl;
import by.epam.autoshow.util.manager.PagePathManager;
import by.epam.autoshow.util.manager.PagePathProperty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class GetCarEditPageCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    public static final String PARAM_CAR_ID = "carId";
    private static final String PARAM_CAR = "car";

    @Override
    public String execute(SessionRequestContent content) {
        String page = null;
        String carId = content.getRequestParameter(PARAM_CAR_ID);
        try {
            CarService carService = CarServiceImpl.getInstance();
            Optional<Car> car = carService.findCarById(Long.parseLong(carId));
            logger.debug("Car: " + car);
            content.setRequestAttributes(PARAM_CAR, car.get());
        } catch (ServiceException e) {
            logger.error(e);
        }
        page = PagePathManager.getProperty(PagePathProperty.CAR_EDIT_PAGE_PROPERTY);
        return page;
    }
}
