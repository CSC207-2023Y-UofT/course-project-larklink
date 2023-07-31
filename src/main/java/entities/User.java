package entities;

import org.mindrot.jbcrypt.BCrypt;

public class User {
    static private Integer userID;
    static private String username;
    static private String password;

    public static void setUser(Integer userID, String username, String plainPassword) {
        User.userID = userID;
        User.username = username;
        User.password = hashPassword(plainPassword);
    }

    public static Integer getUserID() {
        return userID;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    // hash a password for the first time
    public static String hashPassword(String plainPassword){
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    // check that a plain password matches one that has previously been hashed
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
