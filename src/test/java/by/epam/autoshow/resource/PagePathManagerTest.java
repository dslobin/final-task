package by.epam.autoshow.resource;

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
                {PagePathPropertyProvider.INDEX_PAGE_PROPERTY, "/jsp/index.jsp"},
                {PagePathPropertyProvider.LOGIN_PAGE_PROPERTY, "/jsp/login.jsp"},
                {PagePathPropertyProvider.REGISTRATION_PAGE_PROPERTY, "/jsp/registration.jsp"},
                {PagePathPropertyProvider.ERROR_PAGE_PROPERTY, "/jsp/error.jsp"}
        };
    }
}