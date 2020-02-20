package by.epam.autoshow.dao.manager;

import by.epam.autoshow.dao.DaoException;
import by.epam.autoshow.db.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class that manages database connections for DAO classes to work
 *
 * @author Daniil Slobin
 * @see AutoShowServiceManager
 * @see CarManager
 * @see CustomerManger
 * @see OrderManager
 * @see UserManager
 */
abstract class DaoManager {
    private ConnectionPool connectionPool = ConnectionPool.INSTANCE;

    /**
     * Default constructor
     */
    public DaoManager() {
    }

    /**
     * Gets a connection from connection pool
     *
     * @return connection
     * @throws DaoException if data storage access errors occurs
     */
    public Connection getConnection() throws DaoException {
        return connectionPool.getConnection();
    }

    /**
     * @return connection with disabled auto commit mode
     * @throws SQLException if database access errors occurs
     */
    public Connection getTXNConnection() throws DaoException, SQLException {
        Connection connection = connectionPool.getConnection();
        connection.setAutoCommit(false);
        return connection;
    }

    /**
     * Close the connection, if autocommit mode disabled, set autoСommit(true)
     *
     * @param connection to be closed
     * @throws DaoException id data base errors occurs
     */
    public void close(Connection connection) throws DaoException {
        try {
            if (!connection.getAutoCommit()) {
                connection.setAutoCommit(true);
            }
            connectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DaoException("Error closing connection: ", e);
        }
    }
}