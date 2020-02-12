package by.epam.autoshow.dao;

import by.epam.autoshow.model.Color;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public interface ColorDao {

    /**
     * Find the object by id.
     *
     * @param id entity unique identifier
     * @return optional of entity object
     * @throws DaoException if a database access error occurs
     */
    Optional<Color> findById(long id) throws DaoException;

    /**
     * Find entity objects in data storage.
     *
     * @return list of objects
     * @throws DaoException if a database access error occurs
     */
    List<Color> findAll() throws DaoException;

    /**
     * Close statement.
     *
     * @see Statement
     */
    void close(Statement statement);

    /**
     * Close result set.
     *
     * @see ResultSet
     */
    void close(ResultSet resultSet);
}
