package by.epam.autoshow.service.impl;

import by.epam.autoshow.dao.manager.ManagerException;
import by.epam.autoshow.dao.manager.UserManager;
import by.epam.autoshow.model.User;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.UserService;

import by.epam.autoshow.util.security.Sha256PasswordEncoder;
import by.epam.autoshow.validation.UserDataValidator;
import by.epam.autoshow.validation.ValidatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static volatile UserServiceImpl INSTANCE;
    private UserManager userManager;
    private UserDataValidator userValidator;
    private static final Logger logger = LogManager.getLogger();

    private UserServiceImpl() {
        userValidator = new UserDataValidator();
        userManager = UserManager.getInstance();
    }

    public static UserServiceImpl getInstance() {
        UserServiceImpl userService = INSTANCE;
        if (userService == null) {
            synchronized (UserServiceImpl.class) {
                userService = INSTANCE;
                if (userService == null) {
                    INSTANCE = userService = new UserServiceImpl();
                }
            }
        }
        return userService;
    }

    @Override
    public boolean registerUser(User user) throws ServiceException, ValidatorException {
        if (!userValidator.validate(user)) {
            throw new ValidatorException("Failed to insert row, user data not valid!");
        }
        try {
            String password = user.getPassword();
            password = Sha256PasswordEncoder.encode(password);
            user.setPassword(password);
            userManager.addUser(user);
        } catch (ManagerException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public User updateUser(User user) throws ServiceException, ValidatorException {
        if (!userValidator.validate(user)) {
            throw new ValidatorException("Failed to update row, user data not valid!");
        }
        try {
            Optional<User> authorizedUser = userManager.authorizeUser(user.getUsername(), user.getPassword());
            if (authorizedUser.isEmpty()) {
                logger.debug("PASSWORD WAS CHANGED!");
                String password = user.getPassword();
                password = Sha256PasswordEncoder.encode(password);
                user.setPassword(password);
            }
            userManager.updateUser(user);
        } catch (ManagerException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public Optional<User> findUserById(long id) throws ServiceException {
        Optional<User> user = Optional.empty();
        try {
            user = userManager.findById(id);
        } catch (ManagerException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) throws ServiceException {
        Optional<User> user = Optional.empty();
        try {
            user = userManager.findByUsername(username);
        } catch (ManagerException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public Optional<User> authorizeUser(String login, String password) throws ServiceException {
        Optional<User> registeredUser = Optional.empty();
        try {
            registeredUser = userManager.authorizeUser(login, password);
        } catch (ManagerException e) {
            throw new ServiceException(e);
        }
        return registeredUser;
    }

    @Override
    public List<User> findAllUsers() throws ServiceException {
        List<User> users = new ArrayList<>();
        try {
            users = userManager.findUserList();
        } catch (ManagerException e) {
            throw new ServiceException(e);
        }
        return users;
    }
}