package by.epam.autoshow.util.provider;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Locale;

import static org.testng.Assert.*;

public class MessageProviderTest {

    @Test(dataProvider = "enMessageProvider")
    public void testGetEnProperty(String actual, String expected) {
        Locale.setDefault(new Locale("en", "US"));
        String message = MessageProvider.getProperty(actual);
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