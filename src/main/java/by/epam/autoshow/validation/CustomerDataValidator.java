package by.epam.autoshow.validation;

import by.epam.autoshow.model.Customer;

import java.util.regex.Pattern;

public class CustomerDataValidator implements AbstractValidator<Customer> {
    private static final int MIN_SURNAME_LENGTH = 2;
    private static final int MAX_SURNAME_LENGTH = 30;
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 30;
    private static final Pattern EMAIL_PATTERN = Pattern
            .compile("^([\\p{L}\\w-\\.]+){1,64}@([\\p{L}&&[^_]]+){2,255}.[a-z]{2,}$");
    private static final int MAX_EMAIL_LENGTH = 120;
    private static final Pattern PHONE_NUMBER_PATTERN = Pattern
            .compile("^(((8[\\- ]?)(\\(?\\d{3}\\)?[\\- ]?))|(\\+375[\\-]?)(\\(?\\d{2}\\)?[\\- ]?))?" +
                    "[\\d]{3}[\\-]?[\\d]{2}[\\-]?[\\d]{2}$");
    private static final int MAX_PHONE_NUMBER_LENGTH = 20;

    boolean isSurnameValid(String surname) {
        if (surname == null) {
            return false;
        }
        int surnameLength = surname.length();
        return surnameLength > MIN_SURNAME_LENGTH && surnameLength < MAX_SURNAME_LENGTH;
    }

    boolean isNameValid(String name) {
        if (name == null) {
            return false;
        }
        int nameLength = name.length();
        return nameLength > MIN_NAME_LENGTH && nameLength < MAX_NAME_LENGTH;
    }

    boolean isEmailValid(String email) {
        if (email == null) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches() && email.length() < MAX_EMAIL_LENGTH;
    }

    boolean isPhoneNumberValid(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        return PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches() && phoneNumber.length() < MAX_PHONE_NUMBER_LENGTH;
    }

    @Override
    public boolean validate(Customer customer) {
        return isSurnameValid(customer.getSurname()) && isNameValid(customer.getName()) &&
                isEmailValid(customer.getEmail()) && isPhoneNumberValid(customer.getPhoneNumber());
    }
}