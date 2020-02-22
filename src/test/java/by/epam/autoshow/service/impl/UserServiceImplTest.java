package by.epam.autoshow.service.impl;

import by.epam.autoshow.dao.DaoException;
import by.epam.autoshow.dao.impl.UserDaoImpl;
import by.epam.autoshow.model.User;
import by.epam.autoshow.service.ServiceException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import by.epam.autoshow.validation.ValidatorException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceImplTest {
    @Mock
    private UserDaoImpl userDao;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterUser() throws DaoException, ServiceException, ValidatorException {
        when(userDao.insert(any(User.class))).thenReturn(true);
        User user = new User();
        assertThat(userService.registerUser(user), is(notNullValue()));
    }
}