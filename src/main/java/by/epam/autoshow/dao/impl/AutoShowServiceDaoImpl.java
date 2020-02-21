package by.epam.autoshow.dao.impl;

import by.epam.autoshow.dao.AutoShowServiceDao;
import by.epam.autoshow.dao.DaoException;
import by.epam.autoshow.dao.SqlColumnName;
import by.epam.autoshow.model.AutoShowService;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AutoShowServiceDaoImpl implements AutoShowServiceDao {
    private Connection connection;

    private static final String INSERT =
            "INSERT INTO services (title, cost, description) VALUES (?, ?, ?)";

    private static final String UPDATE =
            "UPDATE services SET title = ?, cost = ?, description = ? WHERE service_id = ?";

    private static final String DELETE =
            "DELETE FROM services WHERE service_id = ?";

    private static final String FIND_ALL =
            "SELECT service_id, title, cost, description FROM services";

    private static final String FIND_BY_ID =
            "SELECT service_id, title, cost, description FROM services WHERE service_id = ?";

    public AutoShowServiceDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean insert(AutoShowService autoShowService) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, autoShowService.getTitle());
            preparedStatement.setBigDecimal(2, autoShowService.getCost());
            preparedStatement.setString(3, autoShowService.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return true;
    }

    @Override
    public Optional<AutoShowService> findById(long id) throws DaoException {
        AutoShowService autoShowService = new AutoShowService();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                if (resultSet.next()) {
                    autoShowService.setServiceId(resultSet.getLong(SqlColumnName.SERVICE_ID));
                    autoShowService.setTitle(resultSet.getString(SqlColumnName.TITLE));
                    autoShowService.setCost(resultSet.getBigDecimal(SqlColumnName.COST));
                    autoShowService.setDescription(resultSet.getString(SqlColumnName.DESCRIPTION));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.of(autoShowService);
    }

    @Override
    public AutoShowService update(AutoShowService autoShowService) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, autoShowService.getTitle());
            preparedStatement.setBigDecimal(2, autoShowService.getCost());
            preparedStatement.setString(3, autoShowService.getDescription());
            preparedStatement.setLong(4, autoShowService.getServiceId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return autoShowService;
    }

    @Override
    public boolean delete(AutoShowService autoShowService) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, autoShowService.getServiceId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return true;
    }

    @Override
    public List<AutoShowService> findAll() throws DaoException {
        List<AutoShowService> autoShowServiceList = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
            while (resultSet.next()) {
                AutoShowService autoShowService = new AutoShowService();
                autoShowService.setServiceId(resultSet.getLong(SqlColumnName.SERVICE_ID));
                autoShowService.setTitle(resultSet.getString(SqlColumnName.TITLE));
                autoShowService.setCost(resultSet.getBigDecimal(SqlColumnName.COST));
                autoShowService.setDescription(resultSet.getString(SqlColumnName.DESCRIPTION));
                autoShowServiceList.add(autoShowService);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return autoShowServiceList;
    }
}