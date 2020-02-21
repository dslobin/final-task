package by.epam.autoshow.dao.manager;

import by.epam.autoshow.dao.DaoException;
import by.epam.autoshow.dao.impl.UserDaoImpl;
import by.epam.autoshow.model.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserManager extends DaoManager {
    private static volatile UserManager INSTANCE;
    private static final Logger logger = LogManager.getLogger();

    private UserManager() {

    }

    public static UserManager getInstance() {
        UserManager userManager = INSTANCE;
        if (userManager == null) {
            synchronized (UserManager.class) {
                userManager = INSTANCE;
                if (userManager == null) {
                    INSTANCE = userManager = new UserManager();
                }
            }
        }
        return userManager;
    }

    public boolean addUser(User user) throws ManagerException {
        Connection connection = getConnection();
        try {
            UserDaoImpl userDao = new UserDaoImpl(connection);
            userDao.insert(user);
        } catch (DaoException e) {
            throw new ManagerException(e);
        } finally {
            close(connection);
        }
        return true;
    }

    public Optional<User> findById(long id) throws ManagerException {
        Connection connection = getConnection();
        Optional<User> user = Optional.empty();
        try {
            UserDaoImpl userDao = new UserDaoImpl(connection);
            user = userDao.findById(id);
        } catch (DaoException e) {
            throw new ManagerException(e);
        } finally {
            close(connection);
        }
        return user;
    }

    public Optional<User> findByUsername(String username) throws ManagerException {
        Connection connection = getConnection();
        Optional<User> user = Optional.empty();
        try {
            UserDaoImpl userDao = new UserDaoImpl(connection);
            user = userDao.findByUsername(username);
        } catch (DaoException e) {
            throw new ManagerException(e);
        } finally {
            close(connection);
        }
        return user;
    }

    public Optional<User> authorizeUser(String login, String password) throws ManagerException {
        Connection connection = getConnection();
        User user = new User();
        user.setUsername(login);
        user.setPassword(password);
        Optional<User> registeredUser = Optional.empty();
        try {
            UserDaoImpl userDao = new UserDaoImpl(connection);
            registeredUser = userDao.authorizeUser(user);
        } catch (DaoException e) {
            throw new ManagerException(e);
        } finally {
            close(connection);
        }
        return registeredUser;
    }

    public List<User> findUserList() throws ManagerException {
        Connection connection = getConnection();
        List<User> userList = new ArrayList<>();
        try {
            UserDaoImpl userDao = new UserDaoImpl(connection);
            userList = userDao.findAll();
        } catch (DaoException e) {
            throw new ManagerException(e);
        } finally {
            close(connection);
        }
        return userList;
    }

    public User updateUser(User user) throws ManagerException {
        Connection connection = getConnection();
        try {
            UserDaoImpl userDao = new UserDaoImpl(connection);
            userDao.update(user);
        } catch (DaoException e) {
            throw new ManagerException(e);
        } finally {
            close(connection);
        }
        return user;
    }
}