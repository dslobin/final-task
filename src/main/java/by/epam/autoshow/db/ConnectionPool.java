package by.epam.autoshow.db;

import com.mysql.cj.jdbc.MysqlDataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;


//FIXME: refactor connection pool initialize method
public enum ConnectionPool {
    INSTANCE;

    private String url;
    private String user;
    private String password;
    private BlockingQueue<Connection> freeConnections;
    private Queue<Connection> usedConnections;
    private final static int INITIAL_POOL_SIZE = 3;
    private Logger logger = null;

    ConnectionPool() {
        logger = LogManager.getLogger();
        initialize();
        usedConnections = new ArrayDeque<>();
    }

    private void initialize() {
        freeConnections = new LinkedBlockingDeque<>(INITIAL_POOL_SIZE);
        MysqlDataSource mysqlDataSource = null;
        try {
            mysqlDataSource = MySQLDbInitializeManager.createMySQLDataSourceFactory();
            for (int i = 0; i < INITIAL_POOL_SIZE; ++i) {
                url = mysqlDataSource.getUrl();
                user = mysqlDataSource.getUser();
                password = mysqlDataSource.getPassword();
                freeConnections.add(createConnection(url, user, password));
            }
        } catch (SQLException | IllegalStateException e) {
            logger.fatal("Error initializing connection pool: ", e);
            throw new RuntimeException(e);
        }
        logger.debug("Connection pool was initialized");
    }

    private ProxyConnection createConnection(String url, String user, String password)
            throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        ProxyConnection proxyConnection = new ProxyConnection(connection);
        return proxyConnection;
    }

    public ProxyConnection getConnection() {
        Connection proxyConnection = null;
        try {
            proxyConnection = freeConnections.take();
            usedConnections.offer(proxyConnection);
        } catch (InterruptedException e) {
            logger.error(e);
        }
        return (ProxyConnection) proxyConnection;
    }

    public void releaseConnection(Connection connection) {
        if (connection.getClass() == ProxyConnection.class) {
            usedConnections.remove(connection);
            freeConnections.offer(connection);
        }
    }

    public void destroyPool() {
        for (int i = 0; i < INITIAL_POOL_SIZE; ++i) {
            try {
                freeConnections.take().close();
            } catch (SQLException | InterruptedException e) {
                logger.error("Error destroying connection pool: ", e);
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
                logger.debug("Deregister driver: " + driver);
            } catch (SQLException e) {
                logger.error("Error while unregistering driver: " + driver, e);
            }
        });
    }
}
