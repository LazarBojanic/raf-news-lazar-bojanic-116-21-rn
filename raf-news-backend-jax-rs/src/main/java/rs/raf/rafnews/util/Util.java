package rs.raf.rafnews.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Util {
    public static String ROLE_CONTENT_CREATOR = "content_creator";
    public static String ROLE_ADMIN = "admin";
    public static String ROLE_GUEST = "guest";
    public static Path workingDirectory = Paths.get(System.getProperty("user.dir"));
}
