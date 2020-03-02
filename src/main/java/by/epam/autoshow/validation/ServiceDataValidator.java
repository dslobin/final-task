package by.epam.autoshow.validation;

import by.epam.autoshow.model.AutoShowService;

import java.util.regex.Pattern;

public class ServiceDataValidator implements AbstractValidator<AutoShowService> {
    private static final int MAX_TITLE_LENGTH = 512;
    private static final int MAX_DESCRIPTION_LENGTH = 2048;
    private static final Pattern DOUBLE_REGEX = Pattern.compile("\\d+\\.\\d+");

    private boolean isTitleValid(String title) {
        if (title == null) {
            return false;
        }
        int titleLength = title.length();
        return titleLength < MAX_TITLE_LENGTH;
    }

    private boolean isCostValid(String cost) {
        if (cost == null) {
            return false;
        }
        return DOUBLE_REGEX.matcher(cost).matches();
    }

    private boolean isDescriptionValid(String description) {
        if (description == null) {
            return false;
        }
        int descriptionLength = description.length();
        return descriptionLength < MAX_DESCRIPTION_LENGTH;
    }

    @Override
    public boolean validate(AutoShowService autoShowService) {
        if (autoShowService == null) {
            return false;
        }
        return isTitleValid(autoShowService.getTitle()) && isCostValid(autoShowService.getCost().toString()) &&
                isDescriptionValid(autoShowService.getDescription());
    }
}
