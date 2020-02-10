package by.epam.autoshow.validation;

import org.testng.annotations.*;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;


public class CustomerDataValidatorTest {

    private CustomerDataValidator customerDataValidator;

    @BeforeClass
    public void setUp() {
        customerDataValidator = new CustomerDataValidator();
    }

    @AfterClass
    public void tearDown() {
        customerDataValidator = null;
    }

    @Test
    public void testValidSurname() {
        String surname = "Jhon";
        boolean condition = customerDataValidator.isSurnameValid(surname);
        assertTrue(condition);
    }

    @Test
    public void testValidName() {
        String name = "Doe";
        boolean condition = customerDataValidator.isNameValid(name);
        assertTrue(condition);
    }

    @Test
    public void testInvalidShortSurname() {
        String surname = "S";
        boolean condition = customerDataValidator.isSurnameValid(surname);
        assertFalse(condition);
    }

    @Test
    public void testInvalidShortName() {
        String name = "D";
        boolean condition = customerDataValidator.isNameValid(name);
        assertFalse(condition);
    }

    @Test
    public void testInvalidLongSurname() {
        String surname = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt";
        boolean condition = customerDataValidator.isSurnameValid(surname);
        assertFalse(condition);
    }

    @Test
    public void testInvalidLongName() {
        String name = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt";
        boolean condition = customerDataValidator.isNameValid(name);
        assertFalse(condition);
    }

    @Test(dataProvider = "testEmails", dataProviderClass = EmailDataProvider.class)
    public void testIsEmailValid(String email, boolean expected) {
        boolean actual = customerDataValidator.isEmailValid(email);
        assertEquals(expected, actual);
    }

    @Test(dataProvider = "testPhoneNumbers", dataProviderClass = PhoneNumberDataProvider.class)
    public void testIsPhoneNumberValid(String phoneNumber, boolean expected) {
        boolean actual = customerDataValidator.isPhoneNumberValid(phoneNumber);
        assertEquals(expected, actual);
    }
}