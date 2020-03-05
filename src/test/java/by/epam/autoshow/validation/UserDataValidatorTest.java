package by.epam.autoshow.validation;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserDataValidatorTest {
    private UserDataValidator userDataValidator;

    @BeforeClass
    public void setUp() {
        userDataValidator = new UserDataValidator();
    }

    @AfterClass
    public void tearDown() {
        userDataValidator = null;
    }

    @Test
    public void testValidUsername() {
        String username = "username";
        boolean condition = userDataValidator.isUsernameValid(username);
        assertTrue(condition);
    }

    @Test
    public void testValidPassword() {
        String password = "password";
        boolean condition = userDataValidator.isUsernameValid(password);
        assertTrue(condition);
    }

    @Test
    public void testInvalidUsername() {
        String username = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt" +
                " ut labore et dolore magna aliqua.";
        boolean condition = userDataValidator.isUsernameValid(username);
        assertFalse(condition);
    }

    @Test
    public void testInvalidPassword() {
        String password = "pass";
        boolean condition = userDataValidator.isPasswordValid(password);
        assertFalse(condition);
    }
}