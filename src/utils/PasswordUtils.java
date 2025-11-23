package utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {
    public static String hashPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static Boolean checkPassword(String password, String hashPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //String actualPassword = hashPassword(password);
        return encoder.matches(password, hashPassword);
    }

// Tests de la fonction
    public static void main(String[] args) {
        String password = "123456";
        String hashPassword = hashPassword(password);
        System.out.println(hashPassword);

        System.out.println(checkPassword("123456", "$2a$10$zusUW6nI7yBwxUKxkTYc/OTOzLuc2oLcgkVCJyG9Wicb5BNwvFXDm"));
    }
}
