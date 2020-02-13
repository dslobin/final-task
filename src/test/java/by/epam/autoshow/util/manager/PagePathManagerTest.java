package by.epam.autoshow.util.manager;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PagePathManagerTest {

    @Test(dataProvider = "pagePathProvider")
    public void testGetProperty(String actual, String expected) {
        String actualPagePath = PagePathManager.getProperty(actual);
        assertEquals(actualPagePath, expected);
    }

    @DataProvider(name = "pagePathProvider")
    public static Object[][] provideData() {
        return new Object[][]{
                {PagePathProperty.INDEX_PAGE_PROPERTY, "/jsp/index.jsp"},
                {PagePathProperty.LOGIN_PAGE_PROPERTY, "/jsp/login.jsp"},
                {PagePathProperty.REGISTRATION_PAGE_PROPERTY, "/jsp/registration.jsp"},
                {PagePathProperty.ERROR_PAGE_PROPERTY, "/jsp/error.jsp"}
        };
    }
}