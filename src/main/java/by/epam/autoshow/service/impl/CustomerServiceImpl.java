package by.epam.autoshow.service.impl;

import by.epam.autoshow.dao.manager.CustomerManger;
import by.epam.autoshow.dao.manager.ManagerException;
import by.epam.autoshow.dao.manager.UserManager;
import by.epam.autoshow.model.Customer;
import by.epam.autoshow.model.User;
import by.epam.autoshow.service.CustomerService;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.util.security.Sha256PasswordEncoder;
import by.epam.autoshow.validation.CustomerDataValidator;
import by.epam.autoshow.validation.ValidatorException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

import java.util.*;

public class CustomerServiceImpl implements CustomerService {
    private static volatile CustomerServiceImpl INSTANCE;
    private CustomerManger customerManger;
    private CustomerDataValidator customerValidator;
    private static final Logger logger = LogManager.getLogger();

    private CustomerServiceImpl() {
        customerValidator = new CustomerDataValidator();
        customerManger = CustomerManger.getInstance();
    }

    public static CustomerServiceImpl getInstance() {
        CustomerServiceImpl customerService = INSTANCE;
        if (customerService == null) {
            synchronized (CustomerServiceImpl.class) {
                customerService = INSTANCE;
                if (customerService == null) {
                    INSTANCE = customerService = new CustomerServiceImpl();
                }
            }
        }
        return customerService;
    }

    @Override
    public boolean updateCustomer(User user, Customer customer) throws ServiceException, ValidatorException {
        if (!customerValidator.validate(customer)) {
            throw new ValidatorException("Failed to update row, customer data not valid");
        }
        try {
            boolean isPasswordChanged = checkIfUserChangedPassword(user);
            if (isPasswordChanged) {
                logger.debug("PASSWORD WAS CHANGED!");
                String password = user.getPassword();
                password = Sha256PasswordEncoder.encode(password);
                user.setPassword(password);
            }
            logger.debug("USER DIDN'T CHANGED PASSWORD!");
            customerManger.updateCustomer(user, customer);
        } catch (ManagerException | SQLException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    private boolean checkIfUserChangedPassword(User user) throws ManagerException {
        UserManager userManager = UserManager.getInstance();
        Optional<User> authorizedUser = userManager.authorizeUser(user.getUsername(), user.getPassword());
        return authorizedUser.isPresent();
    }

    @Override
    public Optional<Customer> findCustomerById(long id) throws ServiceException {
        Optional<Customer> customer = Optional.empty();
        try {
            customer = customerManger.findById(id);
        } catch (ManagerException e) {
            throw new ServiceException(e);
        }
        return customer;
    }

    @Override
    public Optional<Customer> findCustomerByLogin(String login) throws ServiceException {
        Optional<Customer> customer = Optional.empty();
        try {
            customer = customerManger.findCustomerByLogin(login);
        } catch (ManagerException e) {
            throw new ServiceException(e);
        }
        return customer;
    }

    @Override
    public List<Customer> findAllCustomers() throws ServiceException {
        List<Customer> customers = new ArrayList<>();
        try {
           customers = customerManger.findCustomerList();
        } catch (ManagerException e) {
            throw new ServiceException(e);
        }
        return customers;
    }

    @Override
    public boolean registerCustomer(User user, Customer customer) throws ServiceException, ValidatorException {
        if (!customerValidator.validate(customer)) {
            throw new ValidatorException("Failed to insert row, customer data not valid");
        }
        try {
            String password = Sha256PasswordEncoder.encode(user.getPassword());
            user.setPassword(password);
            customerManger.insertCustomer(user, customer);
        } catch (ManagerException | SQLException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public Map<String, Customer> findCustomerUserNames() throws ServiceException {
        Map<String, Customer> customers = new HashMap<>();
        try {
             customers = customerManger.findCustomerUserNames();
        } catch (ManagerException e) {
            throw new ServiceException(e);
        }
        return customers;
    }
}