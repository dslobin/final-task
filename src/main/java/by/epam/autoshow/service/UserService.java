package by.epam.autoshow.service;

import by.epam.autoshow.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findUserByUsername(String username) throws ServiceException;

    Optional<User> authorizeUser(String login, String password) throws ServiceException;

    List<User> findAllUsers() throws ServiceException;

    boolean registerUser(User user) throws ServiceException;

    Optional<User> findUserById(long id) throws ServiceException;
}
