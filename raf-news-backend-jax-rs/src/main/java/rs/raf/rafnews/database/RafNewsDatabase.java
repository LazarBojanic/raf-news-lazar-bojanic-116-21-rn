package rs.raf.rafnews.database;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class RafNewsDatabase {
    private static RafNewsDatabase instance = new RafNewsDatabase();
    private DataSource dataSource;

    private RafNewsDatabase() {
        try {
            InitialContext context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/rafNews");
        } catch (Exception e) {
            // Handle naming exception
        }
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

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
