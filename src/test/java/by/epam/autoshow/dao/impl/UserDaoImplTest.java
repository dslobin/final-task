package by.epam.autoshow.dao.impl;

import by.epam.autoshow.dao.DaoException;
import by.epam.autoshow.db.ConnectionPool;
import by.epam.autoshow.model.User;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.util.Optional;

import static org.testng.Assert.*;

public class UserDaoImplTest {

    private UserDaoImpl userDao;
    private ConnectionPool connectionPool;
    private Connection connection;

    @BeforeMethod
    public void setUp() {
        connectionPool = ConnectionPool.INSTANCE;
        connection = connectionPool.getConnection();
        userDao = new UserDaoImpl(connection);
    }

    @AfterMethod
    public void tearDown() {
        connectionPool.releaseConnection(connection);
        connectionPool.destroyPool();
        connectionPool = null;
        connection = null;
        userDao = null;
    }

    @Test
    public void testFindByUsernameReturnTrue() throws DaoException {
        String searchedUsername = "admin";
        Optional<User> userOptional = userDao.findByUsername(searchedUsername);
        assertTrue(userOptional.isPresent());
    }
}