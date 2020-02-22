package by.epam.autoshow.command.impl.car;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.BodyType;
import by.epam.autoshow.model.Car;
import by.epam.autoshow.model.FuelType;
import by.epam.autoshow.model.SaleStatus;
import by.epam.autoshow.service.CarService;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.CarServiceImpl;

import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.util.provider.PagePathProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class AddCarCommand implements ActionCommand {
    private static final String PARAM_MODEL = "model";
    private static final String PARAM_MILEAGE = "mileage";
    private static final String PARAM_FUEL_TYPE = "fuelType";
    private static final String PARAM_BODY_TYPE = "bodyType";
    private static final String PARAM_VOLUME = "volume";
    private static final String PARAM_TRANSMISSION = "transmission";
    private static final String PARAM_DRIVE_UNIT = "driveUnit";
    private static final String PARAM_COLOR = "color";
    private static final String PARAM_ISSUE_YEAR = "issueYear";
    private static final String PARAM_PRICE = "price";
    private static final String PARAM_STATUS = "saleStatus";
    private static final String PARAM_DESCRIPTION = "description";
    private static final String PARAM_IMG_URL = "imgUrl";
    private static final String ERROR_MESSAGE_PROPERTY = "";
    private static final String ATTRIBUTE_ERROR_MESSAGE = "";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        String page = null;
        String model = content.getRequestParameter(PARAM_MODEL);
        String mileage = content.getRequestParameter(PARAM_MILEAGE);
        String fuelType = content.getRequestParameter(PARAM_FUEL_TYPE);
        String bodyType = content.getRequestParameter(PARAM_BODY_TYPE);
        String volume = content.getRequestParameter(PARAM_VOLUME);
        String transmission = content.getRequestParameter(PARAM_TRANSMISSION);
        String driveUnit = content.getRequestParameter(PARAM_DRIVE_UNIT);
        String color = content.getRequestParameter(PARAM_COLOR);
        String issueYear = content.getRequestParameter(PARAM_ISSUE_YEAR);
        String price = content.getRequestParameter(PARAM_PRICE);
        String status = content.getRequestParameter(PARAM_STATUS);
        String description = content.getRequestParameter(PARAM_DESCRIPTION);
        String imageUrl = content.getRequestParameter(PARAM_IMG_URL);
        try {
            Car car = new Car();
            car.setModel(model);
            car.setMileage(Integer.parseInt(mileage));
            car.setFuelType(FuelType.valueOf(fuelType));
            car.setBodyType(BodyType.valueOf(bodyType));
            car.setVolume(Integer.parseInt(volume));
            car.setTransmission(transmission);
            car.setDriveUnit(driveUnit);
            car.setIssueYear(Integer.parseInt(issueYear));
            car.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));
            car.setStatus(SaleStatus.valueOf(status));
            car.setDescription(description);
            car.setImageUrl(imageUrl);
            CarService carService = CarServiceImpl.getInstance();
            carService.addCar(car, color);
        } catch (ServiceException e) {
            logger.error(e);
        }
        page = PagePathProvider.getProperty(PagePathProperty.CAR_EDIT_PAGE_PROPERTY);
        return page;
    }
}