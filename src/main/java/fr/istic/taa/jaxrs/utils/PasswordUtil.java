package fr.istic.taa.jaxrs.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        System.out.println("hashedPassword: " + hashedPassword);
        System.out.println("password: " + password);
        return BCrypt.checkpw(password, hashedPassword);
    }

}
