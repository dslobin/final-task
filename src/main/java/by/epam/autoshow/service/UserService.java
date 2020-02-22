package by.epam.autoshow.service;

import by.epam.autoshow.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Coordinates tasks and delegates work to collaborations of {@link User} objects in the
 * dao manager layer
 *
 * @author Daniil Slobin
 * @see by.epam.autoshow.dao.manager.UserManager
 * @see by.epam.autoshow.service.impl.UserServiceImpl
 */
public interface UserService {
    /**
     * Finds a user by his user name
     *
     * @param username by which to find the user
     * @return {@code Optional} of user
     * @throws ServiceException if dao manager errors occurs
     */
    Optional<User> findByUsername(String username) throws ServiceException;

    /**
     * Finds a user by his login and password
     *
     * @param login    user unique identifier
     * @param password conditional word or set of signs designed to confirm identity or authority
     * @return {@code Optional} of user
     * @throws ServiceException if dao manager errors occurs
     */
    Optional<User> authorizeUser(String login, String password) throws ServiceException;

    /**
     * Finds all users
     *
     * @return a list of users
     * @throws ServiceException
     */
    List<User> findAllUsers() throws ServiceException;

    /**
     * Register user
     *
     * @param user whose you need to register
     * @return {@code true} if user registration was successful
     * @throws ServiceException if dao manager errors occurs
     */
    boolean registerUser(User user) throws ServiceException;

    /**
     * Update user data
     *
     * @param user whose data needs to be updated
     * @return updated user object
     * @throws ServiceException if dao manager errors occurs
     */
    User updateUser(User user) throws ServiceException;

    /**
     * Finds user by id
     *
     * @param id user unique identifier
     * @return {@code Optional} of user
     * @throws ServiceException if dao manager errors occurs
     */
    Optional<User> findUserById(long id) throws ServiceException;
}