package by.epam.autoshow.validation;

import by.epam.autoshow.model.Car;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class CarDataValidator {
    /**
     * Constants for car validation in controller layer
     */
    private static final int MIN_MODEL_LENGTH = 3;
    private static final int MAX_MODEL_LENGTH = 45;
    private static final int MAX_TRANSMISSION_LENGTH = 45;
    private static final int MAX_DRIVE_UNIT_LENGTH = 20;
    private static final int MAX_DESCRIPTION_LENGTH = 4096;
    private static final int MAX_IMAGE_URL_LENGTH = 128;
    private static final Pattern PRICE_REGEX = Pattern.compile("(\\d{1,10}\\.\\d{1,2})|(\\d){1,10}");
    private static final Pattern VOLUME_REGEX = Pattern.compile("\\d{4}");
    private static final Pattern MILEAGE_REGEX = Pattern.compile("\\d{1,7}");
    private static final Pattern ISSUE_YEAR_REGEX = Pattern.compile("\\d{4}");
    /**
     * Constants for car validation in service layer
     */
    private static final int MIN_ISSUE_YEAR = 1970;
    private static final int MAX_ISSUE_YEAR = 2020;
    private static final int MIN_MILEAGE = 0;
    private static final int MAX_MILEAGE = 1_610_000;
    private static final int MIN_VOLUME = 1000;
    private static final int MAX_VOLUME = 8400;
    private static final BigDecimal MIN_PRICE = BigDecimal.valueOf(0.1);
    private static final BigDecimal MAX_PRICE = BigDecimal.valueOf(10_000_000.00);

    public void validateCar(Car car) throws ValidatorException {
        if ((CarDataValidator.MIN_PRICE.compareTo(car.getPrice()) > 0 || CarDataValidator.MAX_PRICE.compareTo(car.getPrice()) < 0) ||
                (CarDataValidator.MIN_ISSUE_YEAR > car.getIssueYear() || CarDataValidator.MAX_ISSUE_YEAR < car.getIssueYear()) ||
                (CarDataValidator.MIN_VOLUME > car.getVolume() || CarDataValidator.MAX_VOLUME < car.getVolume()) ||
                (CarDataValidator.MIN_MILEAGE > car.getMileage() || CarDataValidator.MAX_MILEAGE < car.getMileage())) {
            throw new ValidatorException("Business logic error, car data not valid!");
        }
    }

    public boolean isModelValid(String model) {
        if (model == null || model.isBlank()) {
            return false;
        }
        int modelLength = model.length();
        return modelLength >= MIN_MODEL_LENGTH && modelLength <= MAX_MODEL_LENGTH;
    }

    public boolean isMileageValid(String mileage) {
        if (mileage == null || mileage.isBlank()) {
            return false;
        }
        return MILEAGE_REGEX.matcher(mileage).matches();
    }

    public boolean isVolumeValid(String volume) {
        if (volume == null || volume.isBlank()) {
            return false;
        }
        return VOLUME_REGEX.matcher(volume).matches();
    }

    public boolean isTransmissionValid(String transmission) {
        if (transmission == null || transmission.isBlank()) {
            return false;
        }
        int transmissionLength = transmission.length();
        return transmissionLength < MAX_TRANSMISSION_LENGTH;
    }

    public boolean isDriveUnitValid(String driveUnit) {
        if (driveUnit == null || driveUnit.isBlank()) {
            return false;
        }
        int driveUnitLength = driveUnit.length();
        return driveUnitLength < MAX_DRIVE_UNIT_LENGTH;
    }

    public boolean isIssueYearValid(String issueYear) {
        if (issueYear == null || issueYear.isBlank()) {
            return false;
        }
        return ISSUE_YEAR_REGEX.matcher(issueYear).matches();
    }

    public boolean isPriceValid(String price) {
        if (price == null || price.isBlank()) {
            return false;
        }
        return PRICE_REGEX.matcher(price).matches();
    }

    public boolean isDescriptionValid(String description) {
        if (description != null) {
            int descriptionLength = description.length();
            return descriptionLength < MAX_DESCRIPTION_LENGTH;
        }
        return true;
    }

    public boolean isImageUrlValid(String imageUrl) {
        if (imageUrl != null) {
            int urlLength = imageUrl.length();
            return urlLength < MAX_IMAGE_URL_LENGTH;
        }
        return true;
    }
}