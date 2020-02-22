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

import java.util.List;
import java.util.Optional;

public class GetCarEditPageCommand implements ActionCommand {
    private static final String ATTRIBUTE_CAR_FUEL_TYPE_VALUES = "fuelTypeList";
    private static final String ATTRIBUTE_CAR_BODY_TYPE_VALUES = "bodyTypeList";
    private static final String ATTRIBUTE_SALE_STATUS_VALUES = "saleStatusList";
    private static final String ATTRIBUTE_CAR_COLOR_VALUES = "colorList";
    private static final String PARAM_CAR_ID = "carId";
    private static final String PARAM_CAR = "car";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        String page = null;
        String carId = content.getRequestParameter(PARAM_CAR_ID);
        try {
            CarService carService = CarServiceImpl.getInstance();
            Optional<Car> car = carService.findCarById(Long.parseLong(carId));
            List<Color> colors = carService.findAllColors();
            content.setRequestAttributes(ATTRIBUTE_CAR_COLOR_VALUES, colors);
            content.setRequestAttributes(PARAM_CAR, car.get());
            logger.debug("Car: " + car);
        } catch (ServiceException e) {
            logger.error(e);
        }
        content.setRequestAttributes(ATTRIBUTE_CAR_FUEL_TYPE_VALUES, FuelType.values());
        content.setRequestAttributes(ATTRIBUTE_CAR_BODY_TYPE_VALUES, BodyType.values());
        content.setRequestAttributes(ATTRIBUTE_SALE_STATUS_VALUES, SaleStatus.values());
        page = PagePathProvider.getProperty(PagePathProperty.CAR_EDIT_PAGE_PROPERTY);
        return page;
    }
}
