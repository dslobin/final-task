package by.epam.autoshow.validation;

public class UserDataValidator {
    private static final int MAX_USERNAME_LENGTH = 30;
    private static final int MAX_PASSWORD_LENGTH = 30;
    private static final int MIN_PASSWORD_LENGTH = 6;

    public boolean isUsernameValid(String username) {
        if (username == null) {
            return false;
        }
        int usernameLength = username.length();
        return usernameLength <= MAX_USERNAME_LENGTH;
    }

    public boolean isPasswordValid(String password) {
        if (password == null) {
            return false;
        }
        int passwordLength = password.length();
        return passwordLength >= MIN_PASSWORD_LENGTH && passwordLength <= MAX_PASSWORD_LENGTH;
    }
}
