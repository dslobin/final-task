package by.epam.autoshow.dao.manager;

import by.epam.autoshow.dao.DaoException;
import by.epam.autoshow.db.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

class DaoManager {
    private ConnectionPool connectionPool = ConnectionPool.INSTANCE;

    public DaoManager() {
    }

    public Connection getConnection() throws DaoException {
        return connectionPool.getConnection();
    }

    public Connection getTXNConnection() throws DaoException, SQLException {
        Connection connection = connectionPool.getConnection();
        connection.setAutoCommit(false);
        return connection;
    }

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