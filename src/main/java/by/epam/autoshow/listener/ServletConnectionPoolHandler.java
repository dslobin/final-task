package by.epam.autoshow.listener;

import by.epam.autoshow.db.ConnectionPool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletConnectionPoolHandler implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger();
    private ConnectionPool connectionPool;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.debug("Create connection pool");
        connectionPool = ConnectionPool.INSTANCE;
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.debug("Destroy connection pool");
        connectionPool.destroyPool();
    }
}