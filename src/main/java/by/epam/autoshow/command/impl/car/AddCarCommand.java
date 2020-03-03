package by.epam.autoshow.command.impl.car;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.command.RouteType;
import by.epam.autoshow.command.Router;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.BodyType;
import by.epam.autoshow.model.Car;
import by.epam.autoshow.model.FuelType;
import by.epam.autoshow.model.SaleStatus;
import by.epam.autoshow.service.CarService;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.CarServiceImpl;
import by.epam.autoshow.util.provider.MessagePath;
import by.epam.autoshow.util.provider.MessageProvider;
import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.util.provider.JspPagePath;
import by.epam.autoshow.validation.ValidatorException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;

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
    private static final String PARAM_DESCRIPTION = "carDescription";
    private static final String PARAM_IMG_URL = "imgUrl";
    private static final String ATTRIBUTE_INVALID_CAR = "invalidCar";
    private static final String ATTRIBUTE_CAR_CHANGED = "successfulCarChange";
    private static final String ATTRIBUTE_SERVER_ERROR = "serverError";
    private static final String ATTRIBUTE_CAR_LIST = "carList";
    private static final String CARS_PAGE_URL = "/controller?command=get_all_cars";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(SessionRequestContent content) {
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
        Router router = null;
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
            content.setRequestAttributes(ATTRIBUTE_CAR_CHANGED,
                    MessageProvider.getProperty(MessagePath.CAR_SUCCESSFUL_ADDITION_PROPERTY));
            List<Car> cars = carService.findAllCars();
            content.setRequestAttributes(ATTRIBUTE_CAR_LIST, cars);
            router = new Router(CARS_PAGE_URL, RouteType.REDIRECT);
        } catch (ServiceException e) {
            logger.error(e);
            content.setRequestAttributes(ATTRIBUTE_SERVER_ERROR,
                    MessageProvider.getProperty(MessagePath.SERVER_ERROR_PROPERTY));
            router = new Router(PagePathProvider.getProperty(JspPagePath.ERROR_PAGE_PROPERTY), RouteType.FORWARD);
        } catch (ValidatorException e) {
            logger.error(e);
            content.setRequestAttributes(ATTRIBUTE_INVALID_CAR,
                    MessageProvider.getProperty(MessagePath.INVALID_CAR_ADDITION_PROPERTY));
            router = new Router(PagePathProvider.getProperty(JspPagePath.CAR_EDIT_PAGE_PROPERTY), RouteType.FORWARD);
        }
        return router;
    }
}