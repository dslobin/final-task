package by.epam.autoshow.validation;

import org.testng.annotations.DataProvider;

public class PhoneNumberDataProvider {

    @DataProvider(name = "testPhoneNumbers")
    public static Object[][] provideData() {
        return new Object[][]{
                {"1234567", true},
                {"8029123-45-67", true},
                {"+375(29)123-45-67", true},
                {"123--45-67", false},
                {"++375(29)123-45-67", false},
                {"qwerty", false}
        };
    }
}