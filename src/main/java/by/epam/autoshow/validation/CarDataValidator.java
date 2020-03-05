package by.epam.autoshow.validation;

import java.util.regex.Pattern;

public class CarDataValidator {
    private static final int MIN_MODEL_LENGTH = 3;
    private static final int MAX_MODEL_LENGTH = 45;
    private static final int MAX_TRANSMISSION_LENGTH = 45;
    private static final int MAX_DRIVE_UNIT_LENGTH = 20;
    private static final int MAX_DESCRIPTION_LENGTH = 4096;
    private static final int MAX_IMAGE_URL_LENGTH = 128;
    private static final Pattern PRICE_REGEX = Pattern.compile("(\\d{1,10}\\.\\d{1,2})|(\\d){1,10}");
    private static final Pattern INTEGER_REGEX = Pattern.compile("\\d+");

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
        return INTEGER_REGEX.matcher(mileage).matches();
    }

    public boolean isVolumeValid(String volume) {
        if (volume == null || volume.isBlank()) {
            return false;
        }
        return INTEGER_REGEX.matcher(volume).matches();
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
        return INTEGER_REGEX.matcher(issueYear).matches();
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