package by.epam.autoshow.service.impl;

import by.epam.autoshow.dao.DaoException;
import by.epam.autoshow.dao.impl.AutoShowServiceDaoImpl;
import by.epam.autoshow.db.ConnectionPool;
import by.epam.autoshow.model.carservice.AutoShowService;
import by.epam.autoshow.service.AutoShowServiceManagement;
import by.epam.autoshow.service.ServiceException;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AutoShowServiceManagementImpl implements AutoShowServiceManagement {

    @Override
    public List<AutoShowService> findAllServices() throws ServiceException {
        List<AutoShowService> services = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.getConnection();
        try {
            AutoShowServiceDaoImpl autoShowServiceDao = new AutoShowServiceDaoImpl(connection);
            services = autoShowServiceDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return services;
    }

    @Override
    public boolean addService(AutoShowService autoShowService) throws ServiceException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.getConnection();
        try {
            AutoShowServiceDaoImpl autoShowServiceDao = new AutoShowServiceDaoImpl(connection);
            autoShowServiceDao.insert(autoShowService);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public Optional<AutoShowService> findServiceById(long id) throws ServiceException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.getConnection();
        AutoShowServiceDaoImpl autoShowServiceManagement = new AutoShowServiceDaoImpl(connection);
        Optional<AutoShowService> autoShowService = Optional.empty();
        try {
            autoShowService = autoShowServiceManagement.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return autoShowService;
    }
}
