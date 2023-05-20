package rs.raf.rafnews.database;

import org.postgresql.jdbc.PgConnection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class RafNewsDatabaseRegular {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/rafNews";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "1234";
    private static volatile RafNewsDatabaseRegular instance;

    private RafNewsDatabaseRegular(){
        try{
            Class.forName("org.postgresql.Driver");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static RafNewsDatabaseRegular getInstance() {
        if (instance == null) {
            synchronized (RafNewsDatabaseRegular.class) {
                if (instance == null) {
                    instance = new RafNewsDatabaseRegular();
                }
            }
        }
        return instance;
    }

    public PgConnection getConnection() throws SQLException {
        return (PgConnection) DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }
}
