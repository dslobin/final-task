package by.epam.autoshow.command.impl.car;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.*;
import by.epam.autoshow.service.CarService;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.CarServiceImpl;
import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.util.provider.PagePathProperty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class GetCarImageUploadPage implements ActionCommand {
    private static final String PARAM_CAR_ID = "carId";
    private static final String ATTRIBUTE_CAR = "car";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        String page = null;
        String carId = content.getRequestParameter(PARAM_CAR_ID);
        try {
            CarService carService = CarServiceImpl.getInstance();
            Optional<Car> car = carService.findCarById(Long.parseLong(carId));
            content.setRequestAttributes(ATTRIBUTE_CAR, car.get());
            logger.debug("Car: " + car);
        } catch (ServiceException e) {
            logger.error(e);
        }
        page = PagePathProvider.getProperty(PagePathProperty.FILE_UPLOAD_PAGE_PROPERTY);
        return page;
    }
}
