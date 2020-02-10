package by.epam.autoshow.validation;

import java.util.regex.Pattern;

public class ServiceDataValidator {
    private static final int MAX_TITLE_LENGTH = 512;
    private static final int MAX_DESCRIPTION_LENGTH = 2048;
    private static final Pattern DOUBLE_REGEX = Pattern.compile("\\d+\\.\\d+");

    public boolean isTitleValid(String title) {
        if (title == null) {
            return false;
        }
        int titleLength = title.length();
        return titleLength < MAX_TITLE_LENGTH;
    }

    public boolean isCostValid(String cost) {
        if (cost == null) {
            return false;
        }
        return DOUBLE_REGEX.matcher(cost).matches();
    }

    public boolean isDescriptionValid(String description) {
        if (description == null) {
            return false;
        }
        int descriptionLength = description.length();
        return descriptionLength < MAX_DESCRIPTION_LENGTH;
    }
}
