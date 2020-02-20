package by.epam.autoshow.validation;

import by.epam.autoshow.model.User;
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
    public void testValidUserData() {
        User user = new User("username", "password");
        boolean condition = userDataValidator.validate(user);
        assertTrue(condition);
    }

    @Test
    public void testInvalidUsername() {
        String username = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt" +
                " ut labore et dolore magna aliqua.";
        User user = new User(username, "password");
        boolean condition = userDataValidator.validate(user);
        assertFalse(condition);
    }

    @Test
    public void testInvalidPassword() {
        User user = new User("username", "pass");
        boolean condition = userDataValidator.validate(user);
        assertFalse(condition);
    }
}