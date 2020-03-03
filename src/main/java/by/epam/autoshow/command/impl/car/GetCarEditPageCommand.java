package by.epam.autoshow.command.impl.car;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.command.RouteType;
import by.epam.autoshow.command.Router;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.*;
import by.epam.autoshow.service.CarService;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.CarServiceImpl;
import by.epam.autoshow.util.provider.MessagePath;
import by.epam.autoshow.util.provider.MessageProvider;
import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.util.provider.JspPagePath;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

//FIXME
public class GetCarEditPageCommand implements ActionCommand {
    private static final String ATTRIBUTE_CAR_FUEL_TYPE_VALUES = "fuelTypeList";
    private static final String ATTRIBUTE_CAR_BODY_TYPE_VALUES = "bodyTypeList";
    private static final String ATTRIBUTE_SALE_STATUS_VALUES = "saleStatusList";
    private static final String ATTRIBUTE_CAR_COLOR_VALUES = "colorList";
    private static final String PARAM_CAR_ID = "carId";
    private static final String PARAM_CAR = "car";
    private static final String ATTRIBUTE_SERVER_ERROR = "serverError";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(SessionRequestContent content) {
        String carId = content.getRequestParameter(PARAM_CAR_ID);
        Router router = null;
        try {
            CarService carService = CarServiceImpl.getInstance();
            Optional<Car> car = carService.findCarById(Long.parseLong(carId));
            content.setRequestAttributes(PARAM_CAR, car.get());
            content.setRequestAttributes(ATTRIBUTE_CAR_FUEL_TYPE_VALUES, FuelType.values());
            content.setRequestAttributes(ATTRIBUTE_CAR_BODY_TYPE_VALUES, BodyType.values());
            content.setRequestAttributes(ATTRIBUTE_SALE_STATUS_VALUES, SaleStatus.values());
            List<Color> colors = carService.findAllColors();
            content.setRequestAttributes(ATTRIBUTE_CAR_COLOR_VALUES, colors);
            router = new Router(PagePathProvider.getProperty(JspPagePath.CAR_EDIT_PAGE_PROPERTY),
                    RouteType.FORWARD);
        } catch (ServiceException e) {
            logger.error(e);
            content.setRequestAttributes(ATTRIBUTE_SERVER_ERROR,
                    MessageProvider.getProperty(MessagePath.SERVER_ERROR_PROPERTY));
            router = new Router(PagePathProvider.getProperty(JspPagePath.ERROR_PAGE_PROPERTY), RouteType.FORWARD);
        }
        return router;
    }
}
