package rs.raf.rafnews.database;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class RafNewsDatabase {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/rafNews";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "1234";
    private final DataSource dataSource;

    private static volatile RafNewsDatabase instance;

    private RafNewsDatabase() {
        PoolProperties p = new PoolProperties();
        p.setDriverClassName("org.postgresql.Driver");
        p.setUrl(JDBC_URL);
        p.setUsername(JDBC_USER);
        p.setPassword(JDBC_PASSWORD);
        dataSource = new DataSource();
        dataSource.setPoolProperties(p);
    }

    public static RafNewsDatabase getInstance() {
        if (instance == null) {
            synchronized (RafNewsDatabase.class) {
                if (instance == null) {
                    instance = new RafNewsDatabase();
                }
            }
        }
        return instance;
    }

    public void closeConnection() {
        try {
            if (dataSource != null) {
                dataSource.close();
            }
        } finally {
            deregisterJdbcDriver();
            stopJdbcPoolCleanerThread();
        }
    }

    private void deregisterJdbcDriver() {
        try {
            Enumeration<Driver> drivers =  DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                Driver driver = drivers.nextElement();
                if (driver.getClass().getClassLoader() == getClass().getClassLoader()) {
                    DriverManager.deregisterDriver(driver);
                }
            }
        } catch (SQLException e) {
            // Handle SQL exception
        }
    }


    private void stopJdbcPoolCleanerThread() {
        try {
            Thread jdbcPoolCleanerThread = Thread.getAllStackTraces()
                    .keySet()
                    .stream()
                    .filter(thread -> thread.getName().startsWith("Tomcat JDBC Pool Cleaner"))
                    .findFirst()
                    .orElse(null);

            if (jdbcPoolCleanerThread != null) {
                jdbcPoolCleanerThread.interrupt();
                jdbcPoolCleanerThread.join();
            }
        }
        catch (InterruptedException e) {
            // Handle interruption exception
        }
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
