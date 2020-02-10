package by.epam.autoshow.resource;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Locale;

import static org.testng.Assert.*;

public class MessageManagerTest {

    @Test(dataProvider = "enMessageProvider")
    public void testGetEnProperty(String actual, String expected) {
        Locale.setDefault(new Locale("en", "US"));
        String message = MessageManager.getProperty(actual);
        assertEquals(message, expected);
    }

    @DataProvider(name = "enMessageProvider")
    public static Object[][] provideData() {
        return new Object[][]{
                {"label.loginError", "Login or password is entered incorrectly"},
                {"label.wrongAction", "Command not found or wrong!"}
        };
    }
}