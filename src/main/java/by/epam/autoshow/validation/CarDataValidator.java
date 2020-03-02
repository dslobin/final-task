package by.epam.autoshow.validation;

import by.epam.autoshow.model.Car;

import java.util.regex.Pattern;

public class CarDataValidator implements AbstractValidator<Car> {
    private static final int MIN_MODEL_LENGTH = 3;
    private static final int MAX_MODEL_LENGTH = 45;
    private static final Pattern PRICE_REGEX = Pattern.compile("\\d+\\.\\d+");
    private static final Pattern INTEGER_REGEX = Pattern.compile("\\d+");

    private boolean isModelValid(String model) {
        if (model == null) {
            return false;
        }
        int modelLength = model.length();
        return modelLength >= MIN_MODEL_LENGTH && modelLength <= MAX_MODEL_LENGTH;
    }

    private boolean isPriceValid(String price) {
        if (price == null) {
            return false;
        }
        return PRICE_REGEX.matcher(price).matches();
    }

    private boolean isIssueYearValid(String issueYear) {
        if (issueYear == null) {
            return false;
        }
        return INTEGER_REGEX.matcher(issueYear).matches();
    }

    private boolean isMileageValid(String mileage) {
        if (mileage == null) {
            return false;
        }
        return INTEGER_REGEX.matcher(mileage).matches();
    }

    @Override
    public boolean validate(Car car) {
        if (car == null) {
            return false;
        }
        return isModelValid(car.getModel());
    }
}