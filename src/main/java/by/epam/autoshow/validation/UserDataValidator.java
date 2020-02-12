package by.epam.autoshow.validation;

import by.epam.autoshow.model.User;

public class UserDataValidator implements AbstractValidator<User> {
    private static final int MAX_USERNAME_LENGTH = 30;
    private static final int MAX_PASSWORD_LENGTH = 30;
    private static final int MIN_PASSWORD_LENGTH = 6;

    private boolean isUsernameValid(String username) {
        if (username == null) {
            return false;
        }
        int usernameLength = username.length();
        return usernameLength <= MAX_USERNAME_LENGTH;
    }

    private boolean isPasswordValid(String password) {
        if (password == null) {
            return false;
        }
        int passwordLength = password.length();
        return passwordLength >= MIN_PASSWORD_LENGTH && passwordLength <= MAX_PASSWORD_LENGTH;
    }

    @Override
    public boolean validate(User user) {
        return isUsernameValid(user.getUsername()) && isPasswordValid(user.getPassword());
    }
}
