package by.epam.autoshow.dao.manager;

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
     * @throws ManagerException if data storage access errors occurs
     */
    public Connection getConnection() throws ManagerException {
        return connectionPool.getConnection();
    }

    /**
     * @return connection with disabled auto commit mode
     * @throws SQLException if database access errors occurs
     */
    public Connection getTXNConnection() throws ManagerException, SQLException {
        Connection connection = connectionPool.getConnection();
        connection.setAutoCommit(false);
        return connection;
    }

    /**
     * Close the connection, if autocommit mode disabled, set autoСommit(true)
     *
     * @param connection to be closed
     * @throws ManagerException id data base errors occurs
     */
    public void close(Connection connection) throws ManagerException {
        try {
            if (!connection.getAutoCommit()) {
                connection.setAutoCommit(true);
            }
            connectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new ManagerException("Error closing connection: ", e);
        }
    }
}