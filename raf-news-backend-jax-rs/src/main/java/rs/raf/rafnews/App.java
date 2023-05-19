package rs.raf.rafnews;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import rs.raf.rafnews.api.*;
import rs.raf.rafnews.filter.AuthFilter;
import rs.raf.rafnews.filter.CorsFilter;
import rs.raf.rafnews.util.Util;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class App extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(CorsFilter.class);
        classes.add(AuthFilter.class);
        classes.add(ArticleResource.class);
        classes.add(CategoryResource.class);
        classes.add(CommentResource.class);
        classes.add(ServiceUserResource.class);
        classes.add(TagResource.class);
        return classes;
    }
    public App(){
        System.out.println(Util.workingDirectory.toString());
    }
}