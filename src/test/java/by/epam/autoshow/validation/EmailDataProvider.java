package by.epam.autoshow.validation;

import org.testng.annotations.DataProvider;

public class EmailDataProvider {

    @DataProvider(name = "testEmails")
    public static Object[][] provideData() {
        return new Object[][]{
                {"my-email@gmail.com", true},
                {"email@mail.ru", true},
                {"new.email@tut.by", true},
                {"username@epam.by", true},
                {"1@gmail.com.", false},
                {"address@outlook__.com", false},
                {"email@gmail!.com", false},
                {"another.email@gmail.COM", false}
        };
    }
}