package by.epam.autoshow.db;

import com.mysql.cj.jdbc.MysqlDataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Properties;

class MySQLDbInitializeManager {
    private final static String DATABASE_PROPERTIES_PATH = "config/database.properties";
    private final static String JDBC_DRIVER_PROPERTY = "jdbc.driver";
    private final static String JDBC_URL_PROPERTY = "jdbc.url";
    private final static String JDBC_USER_PROPERTY = "jdbc.username";
    private final static String JDBC_PASSWORD_PROPERTY = "jdbc.password";
    private final static Logger logger = LogManager.getLogger();

    private MySQLDbInitializeManager() {
    }

    public static MysqlDataSource createMySQLDataSourceFactory() throws IllegalStateException {
        Properties properties = new Properties();
        MysqlDataSource dataSource = null;
        try {
            File databaseProperties = new File(Objects.requireNonNull(
                    MySQLDbInitializeManager.class.getClassLoader().getResource(DATABASE_PROPERTIES_PATH)).toURI());
            logger.debug(databaseProperties.getAbsolutePath());
            properties.load(new FileInputStream(databaseProperties.getAbsoluteFile()));
            dataSource = new MysqlDataSource();
            Class.forName(properties.getProperty(JDBC_DRIVER_PROPERTY));
            dataSource.setURL(properties.getProperty(JDBC_URL_PROPERTY));
            dataSource.setUser(properties.getProperty(JDBC_USER_PROPERTY));
            dataSource.setPassword(properties.getProperty(JDBC_PASSWORD_PROPERTY));
        } catch (IOException | ClassNotFoundException | URISyntaxException e) {
            throw new IllegalStateException("Error reading properties file: ", e);
        }
        return dataSource;
    }
}