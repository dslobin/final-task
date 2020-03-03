package by.epam.autoshow.util.provider;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PagePathProviderTest {

    @Test(dataProvider = "pagePathProvider")
    public void testGetProperty(String actual, String expected) {
        String actualPagePath = PagePathProvider.getProperty(actual);
        assertEquals(actualPagePath, expected);
    }

    @DataProvider(name = "pagePathProvider")
    public static Object[][] provideData() {
        return new Object[][]{
                {JspPagePath.INDEX_PAGE_PROPERTY, "/jsp/index.jsp"},
                {JspPagePath.LOGIN_PAGE_PROPERTY, "/jsp/login.jsp"},
                {JspPagePath.REGISTRATION_PAGE_PROPERTY, "/jsp/registration.jsp"},
                {JspPagePath.ERROR_PAGE_PROPERTY, "/jsp/error.jsp"}
        };
    }
}