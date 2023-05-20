package rs.raf.rafnews.database;

import org.postgresql.jdbc.PgConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RafNewsDatabase1 {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/rafNews";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "1234";
    private static volatile RafNewsDatabase1 instance;

    private RafNewsDatabase1(){
        try{
            Class.forName("org.postgresql.Driver");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static RafNewsDatabase1 getInstance() {
        if (instance == null) {
            synchronized (RafNewsDatabase1.class) {
                if (instance == null) {
                    instance = new RafNewsDatabase1();
                }
            }
        }
        return instance;
    }

    public PgConnection getConnection() throws SQLException {
        return (PgConnection) DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }
}
