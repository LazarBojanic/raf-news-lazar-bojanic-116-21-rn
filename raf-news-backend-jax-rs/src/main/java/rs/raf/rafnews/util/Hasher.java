package rs.raf.rafnews.util;

import org.mindrot.jbcrypt.BCrypt;

public class Hasher {

    public static String hashPassword(String pass){
        return BCrypt.hashpw(pass, BCrypt.gensalt());
    }
    public static boolean checkPassword(String pass, String hashedPass){
        return BCrypt.checkpw(pass, hashedPass);
    }
}
