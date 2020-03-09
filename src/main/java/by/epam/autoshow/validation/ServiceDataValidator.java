package by.epam.autoshow.validation;

import by.epam.autoshow.model.AutoShowService;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class ServiceDataValidator {
    /**
     * Constants for car validation in controller layer
     */
    private static final int MAX_TITLE_LENGTH = 512;
    private static final int MAX_DESCRIPTION_LENGTH = 2048;
    private static final Pattern COST_REGEX = Pattern.compile("(\\d{1,6}\\.\\d{1,2})|(\\d){1,6}");
    /**
     * Constants for car validation in service layer
     */
    public static final BigDecimal MIN_PRICE = BigDecimal.valueOf(0.1);
    public static final BigDecimal MAX_PRICE = BigDecimal.valueOf(10_000.00);

    public void validateService(AutoShowService service) throws ValidatorException {
        if (ServiceDataValidator.MIN_PRICE.compareTo(service.getCost()) > 0 ||
                ServiceDataValidator.MAX_PRICE.compareTo(service.getCost()) < 0) {
            throw new ValidatorException("Business logic error, service cost not valid!");
        }
    }

    public boolean isTitleValid(String title) {
        if (title == null || title.isBlank()) {
            return false;
        }
        int titleLength = title.length();
        return titleLength < MAX_TITLE_LENGTH;
    }

    public boolean isCostValid(String cost) {
        if (cost == null || cost.isBlank()) {
            return false;
        }
        return COST_REGEX.matcher(cost).matches();
    }

    public boolean isDescriptionValid(String description) {
        if (description != null) {
            int descriptionLength = description.length();
            return descriptionLength < MAX_DESCRIPTION_LENGTH;
        }
        return true;
    }
}