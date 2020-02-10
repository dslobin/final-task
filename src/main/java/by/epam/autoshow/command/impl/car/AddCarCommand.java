package by.epam.autoshow.command.impl.car;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.car.Car;
import by.epam.autoshow.model.car.FuelType;
import by.epam.autoshow.model.car.SaleStatus;
import by.epam.autoshow.resource.MessageManager;
import by.epam.autoshow.resource.PagePathManager;
import by.epam.autoshow.resource.PagePathPropertyProvider;
import by.epam.autoshow.service.impl.CarServiceImpl;
import by.epam.autoshow.service.ServiceException;

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

    private static final Logger logger = LogManager.getLogger();
    private static final String ERROR_MESSAGE_PROPERTY = "";
    private static final String ATTRIBUTE_ERROR_MESSAGE = "";

    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = null;
        String model = sessionRequestContent.getRequestParameter(PARAM_MODEL);
        String mileage = sessionRequestContent.getRequestParameter(PARAM_MILEAGE);
        String fuelType = sessionRequestContent.getRequestParameter(PARAM_FUEL_TYPE);
        String bodyType = sessionRequestContent.getRequestParameter(PARAM_BODY_TYPE);
        String volume = sessionRequestContent.getRequestParameter(PARAM_VOLUME);
        String transmission = sessionRequestContent.getRequestParameter(PARAM_TRANSMISSION);
        String driveUnit = sessionRequestContent.getRequestParameter(PARAM_DRIVE_UNIT);
        String color = sessionRequestContent.getRequestParameter(PARAM_COLOR);
        String issueYear = sessionRequestContent.getRequestParameter(PARAM_ISSUE_YEAR);
        String price = sessionRequestContent.getRequestParameter(PARAM_PRICE);
        String status = sessionRequestContent.getRequestParameter(PARAM_STATUS);
        String description = sessionRequestContent.getRequestParameter(PARAM_DESCRIPTION);
        String imageUrl = sessionRequestContent.getRequestParameter(PARAM_IMG_URL);
        Car car = new Car();
        car.setModel(model);
        car.setMileage(Integer.parseInt(mileage));
        car.setFuelType(FuelType.valueOf(fuelType));
        car.setVolume(Integer.parseInt(volume));
        car.setTransmission(transmission);
        car.setDriveUnit(driveUnit);
        car.setIssueYear(Integer.parseInt(issueYear));
        car.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));
        car.setStatus(SaleStatus.valueOf(status));
        car.setDescription(description);
        car.setImageUrl(imageUrl);
        CarServiceImpl carServiceImpl = new CarServiceImpl();
        /*try {
            boolean isCarAdded = carServiceImpl.insertCar(car, color, bodyType);
            if (isCarAdded) {
                page = PagePathManager.getProperty(PagePathPropertyProvider.CAR_EDIT_PAGE_PROPERTY);
            } else {
                sessionRequestContent.setRequestAttributes(ATTRIBUTE_ERROR_MESSAGE,
                        MessageManager.getProperty(ERROR_MESSAGE_PROPERTY));
                page = PagePathManager.getProperty(PagePathPropertyProvider.CAR_EDIT_PAGE_PROPERTY);
            }
        } catch (ServiceException e) {
            logger.error(e);
        }*/
        return page;
    }
}