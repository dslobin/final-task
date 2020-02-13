package by.epam.autoshow.dao.manager;

import by.epam.autoshow.dao.impl.UserDaoImpl;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserManagerTest {

    private UserDaoImpl userDao;
    private UserManager userManager;

    @BeforeMethod
    public void setUp() {
        userManager = UserManager.getInstance();
    }

    @AfterMethod
    public void tearDown() {
    }

    @Test
    public void testFindById() {
    }
}