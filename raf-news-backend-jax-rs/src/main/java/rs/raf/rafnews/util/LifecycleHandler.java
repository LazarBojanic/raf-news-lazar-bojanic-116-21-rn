package rs.raf.rafnews.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import rs.raf.rafnews.database.RafNewsDatabase;
import rs.raf.rafnews.exception.*;
import rs.raf.rafnews.model.ServiceUser;
import rs.raf.rafnews.model.ServiceUserRegister;
import rs.raf.rafnews.service.implementation.ServiceUserService;

import java.sql.SQLException;

@WebListener
public class LifecycleHandler implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("App started!!!");
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("App closed!!!");
    }
}
