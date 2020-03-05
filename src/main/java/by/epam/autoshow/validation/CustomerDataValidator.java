package by.epam.autoshow.validation;

import java.util.regex.Pattern;

public class CustomerDataValidator {
    private static final int MIN_SURNAME_LENGTH = 2;
    private static final int MAX_SURNAME_LENGTH = 30;
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 30;
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^([\\p{L}\\d-\\.]+){1,64}@([\\p{L}&&[^_]]+){2,255}.[a-z]{2,}$"
    );
    private static final int MAX_EMAIL_LENGTH = 120;
    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("[\\+]\\d{3}[\\(]*\\d{2}[\\)]*\\d{7}");
    private static final int MAX_PHONE_NUMBER_LENGTH = 20;

    public boolean isSurnameValid(String surname) {
        if (surname == null || surname.isBlank()) {
            return false;
        }
        int surnameLength = surname.length();
        return surnameLength >= MIN_SURNAME_LENGTH && surnameLength <= MAX_SURNAME_LENGTH;
    }

    public boolean isNameValid(String name) {
        if (name == null || name.isBlank()) {
            return false;
        }
        int nameLength = name.length();
        return nameLength >= MIN_NAME_LENGTH && nameLength <= MAX_NAME_LENGTH;
    }

    public boolean isEmailValid(String email) {
        if (email == null || email.isBlank()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches() && email.length() <= MAX_EMAIL_LENGTH;
    }

    public boolean isPhoneNumberValid(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            return false;
        }
        return PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches() && phoneNumber.length() <= MAX_PHONE_NUMBER_LENGTH;
    }
}