package by.epam.autoshow.service.impl;

import by.epam.autoshow.dao.manager.AutoShowServiceManager;
import by.epam.autoshow.dao.manager.ManagerException;
import by.epam.autoshow.model.AutoShowService;
import by.epam.autoshow.service.AutoShowServiceManagement;
import by.epam.autoshow.service.ServiceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AutoShowServiceManagementImpl implements AutoShowServiceManagement {
    private static volatile AutoShowServiceManagementImpl INSTANCE;
    private AutoShowServiceManager autoShowServiceManager;
    private static final Logger logger = LogManager.getLogger();

    private AutoShowServiceManagementImpl() {
        autoShowServiceManager = AutoShowServiceManager.getInstance();
    }

    public static AutoShowServiceManagementImpl getInstance() {
        AutoShowServiceManagementImpl serviceManagement = INSTANCE;
        if (serviceManagement == null) {
            synchronized (AutoShowServiceManagementImpl.class) {
                serviceManagement = INSTANCE;
                if (serviceManagement == null) {
                    INSTANCE = serviceManagement = new AutoShowServiceManagementImpl();
                }
            }
        }
        return serviceManagement;
    }

    @Override
    public List<AutoShowService> findAllServices() throws ServiceException {
        List<AutoShowService> services = new ArrayList<>();
        try {
            services = autoShowServiceManager.findServiceList();
        } catch (ManagerException e) {
            throw new ServiceException(e);
        }
        return services;
    }

    @Override
    public boolean addService(AutoShowService autoShowService) throws ServiceException {
        try {
            autoShowServiceManager.addAutoShowService(autoShowService);
        } catch (ManagerException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public AutoShowService updateService(AutoShowService autoShowService) throws ServiceException {
        try {
            autoShowServiceManager.updateAutoShowService(autoShowService);
        } catch (ManagerException e) {
            throw new ServiceException(e);
        }
        return autoShowService;
    }

    @Override
    public Optional<AutoShowService> findServiceById(long id) throws ServiceException {
        Optional<AutoShowService> autoShowService = Optional.empty();
        try {
            autoShowService = autoShowServiceManager.findServiceById(id);
        } catch (ManagerException e) {
            throw new ServiceException(e);
        }
        return autoShowService;
    }
}
