package by.epam.autoshow.service;

import by.epam.autoshow.model.carservice.AutoShowService;

import java.util.List;
import java.util.Optional;

public interface AutoShowServiceManagement {
    List<AutoShowService> findAllServices() throws ServiceException;
    boolean addService(AutoShowService autoShowService) throws ServiceException;
    Optional<AutoShowService> findServiceById(long id) throws ServiceException;
}