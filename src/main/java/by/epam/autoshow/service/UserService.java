package by.epam.autoshow.service;

import by.epam.autoshow.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> authorizeUser(String login, String password) throws ServiceException;
    List<User> findAllUsers() throws ServiceException;
    Optional<User> findUserById(long id) throws ServiceException;
}
