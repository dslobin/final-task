package by.epam.autoshow.command.impl.car;

import by.epam.autoshow.command.ActionCommand;
import by.epam.autoshow.controller.SessionRequestContent;
import by.epam.autoshow.model.BodyType;
import by.epam.autoshow.model.Color;
import by.epam.autoshow.model.FuelType;
import by.epam.autoshow.model.SaleStatus;
import by.epam.autoshow.service.CarService;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.CarServiceImpl;
import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.util.provider.JspPagePath;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GetCarAddPageCommand implements ActionCommand {
    private static final String ATTRIBUTE_CAR_FUEL_TYPE_VALUES = "fuelTypeList";
    private static final String ATTRIBUTE_CAR_BODY_TYPE_VALUES = "bodyTypeList";
    private static final String ATTRIBUTE_SALE_STATUS_VALUES = "saleStatusList";
    private static final String ATTRIBUTE_CAR_COLOR_VALUES = "colorList";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        try {
            CarService carService = CarServiceImpl.getInstance();
            List<Color> colors = carService.findAllColors();
            content.setRequestAttributes(ATTRIBUTE_CAR_COLOR_VALUES, colors);
        } catch (ServiceException e) {
            logger.error(e);
        }
        content.setRequestAttributes(ATTRIBUTE_CAR_FUEL_TYPE_VALUES, FuelType.values());
        content.setRequestAttributes(ATTRIBUTE_CAR_BODY_TYPE_VALUES, BodyType.values());
        content.setRequestAttributes(ATTRIBUTE_SALE_STATUS_VALUES, SaleStatus.values());
        String page = PagePathProvider.getProperty(JspPagePath.CAR_EDIT_PAGE_PROPERTY);
        return page;
    }
}