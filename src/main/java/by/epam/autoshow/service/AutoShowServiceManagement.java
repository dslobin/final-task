package by.epam.autoshow.service;

import by.epam.autoshow.model.AutoShowService;

import java.util.List;
import java.util.Optional;

/**
 * Coordinates tasks and delegates work to collaborations of {@link AutoShowService} objects in the
 * dao manager layer
 *
 * @author Daniil Slobin
 * @see by.epam.autoshow.dao.manager.AutoShowServiceManager
 * @see by.epam.autoshow.service.impl.AutoShowServiceManagementImpl
 */

public interface AutoShowServiceManagement {
    /**
     * Finds all services
     *
     * @return auto show service list
     * @throws ServiceException if dao manager errors occurs
     */
    List<AutoShowService> findAllServices() throws ServiceException;

    /**
     * Add auto show service
     *
     * @param autoShowService data
     * @return {@code true} if auto show service was added successfully
     * @throws ServiceException if dao manager errors occurs
     */
    boolean addService(AutoShowService autoShowService) throws ServiceException;

    /**
     * Update auto show service
     *
     * @param autoShowService data
     * @return {@code true} if auto show service was updated successfully
     * @throws ServiceException if dao manager errors occurs
     */
    AutoShowService updateService(AutoShowService autoShowService) throws ServiceException;

    /**
     * Finds auto show service by id
     *
     * @param id service unique identifier
     * @return {@code Optional} of auto show service
     * @throws ServiceException if dao manager errors occurs
     */
    Optional<AutoShowService> findServiceById(long id) throws ServiceException;
}