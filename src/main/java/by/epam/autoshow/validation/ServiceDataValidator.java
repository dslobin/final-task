package by.epam.autoshow.validation;

import java.util.regex.Pattern;

public class ServiceDataValidator {
    private static final int MAX_TITLE_LENGTH = 512;
    private static final int MAX_DESCRIPTION_LENGTH = 2048;
    private static final Pattern COST_REGEX = Pattern.compile("(\\d{1,6}\\.\\d{1,2})|(\\d){1,6}");

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