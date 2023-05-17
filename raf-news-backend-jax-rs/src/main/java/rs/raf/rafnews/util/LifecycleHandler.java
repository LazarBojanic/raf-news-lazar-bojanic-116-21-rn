package rs.raf.rafnews.util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import rs.raf.rafnews.database.RafNewsDatabase;

@WebListener
public class LifecycleHandler implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("App started!!!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("App closed!!!");
        RafNewsDatabase.getInstance().closeConnection();
    }
}
