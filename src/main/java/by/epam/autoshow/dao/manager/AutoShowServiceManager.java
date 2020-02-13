package by.epam.autoshow.dao.manager;

import by.epam.autoshow.dao.DaoException;
import by.epam.autoshow.dao.impl.AutoShowServiceDaoImpl;
import by.epam.autoshow.model.AutoShowService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AutoShowServiceManager extends DaoManager {
    private static volatile AutoShowServiceManager INSTANCE;
    private static final Logger logger = LogManager.getLogger();

    private AutoShowServiceManager() {

    }

    public static AutoShowServiceManager getInstance() {
        AutoShowServiceManager autoShowServiceManager = INSTANCE;
        if (autoShowServiceManager == null) {
            synchronized (AutoShowServiceManager.class) {
                autoShowServiceManager = INSTANCE;
                if (autoShowServiceManager == null) {
                    INSTANCE = autoShowServiceManager = new AutoShowServiceManager();
                }
            }
        }
        return autoShowServiceManager;
    }

    public List<AutoShowService> findServiceList() throws DaoException {
        Connection connection = getConnection();
        List<AutoShowService> services = new ArrayList<>();
        try {
            AutoShowServiceDaoImpl autoShowServiceDao = new AutoShowServiceDaoImpl(connection);
            services = autoShowServiceDao.findAll();
        } catch (DaoException e) {
            throw new DaoException(e);
        } finally {
            close(connection);
        }
        return services;
    }

    public boolean addAutoShowService(AutoShowService autoShowService) throws DaoException {
        Connection connection = getConnection();
        try {
            AutoShowServiceDaoImpl autoShowServiceDao = new AutoShowServiceDaoImpl(connection);
            autoShowServiceDao.insert(autoShowService);
        } catch (DaoException e) {
            throw new DaoException(e);
        } finally {
            close(connection);
        }
        return true;
    }

    public AutoShowService updateAutoShowService(AutoShowService autoShowService) throws DaoException {
        Connection connection = getConnection();
        try {
            AutoShowServiceDaoImpl autoShowServiceDao = new AutoShowServiceDaoImpl(connection);
            autoShowServiceDao.update(autoShowService);
        } catch (DaoException e) {
            throw new DaoException(e);
        } finally {
            close(connection);
        }
        return autoShowService;
    }

    public Optional<AutoShowService> findServiceById(long id) throws DaoException {
        Connection connection = getConnection();
        Optional<AutoShowService> autoShowService = Optional.empty();
        try {
            AutoShowServiceDaoImpl autoShowServiceManagement = new AutoShowServiceDaoImpl(connection);
            autoShowService = autoShowServiceManagement.findById(id);
        } catch (DaoException e) {
            throw new DaoException(e);
        } finally {
            close(connection);
        }
        return autoShowService;
    }
}