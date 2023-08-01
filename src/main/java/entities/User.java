package entities;

import org.mindrot.jbcrypt.BCrypt;

/**
 * This User class represents a user in the system and employs a Singleton-like pattern with all static members.
 * It uses BCrypt for password hashing and checking.
 */
public class User {
    private static Integer userID;
    private static String username;
    private static String password;

    /**
     * Set the user details. This method also hashes the password before storing it.
     * @param userID The unique identifier for the user.
     * @param username The username of the user.
     * @param plainPassword The plain text password that will be hashed and stored.
     */
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

    /**
     * Hash a password for the first time using BCrypt.
     * @param plainPassword The plain text password to be hashed.
     * @return The hashed password.
     */
    public static String hashPassword(String plainPassword){
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    /**
     * Check that a plain password matches one that has previously been hashed.
     * @param plainPassword The plain text password to be checked.
     * @param hashedPassword The hashed password to check against.
     * @return true if the passwords match, false otherwise.
     */
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
