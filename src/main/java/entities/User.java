package entities;

import org.mindrot.jbcrypt.BCrypt;

/**
 * This class represents a logged-in user and employs a Singleton-like pattern with all static members since only one
 * user can be logged in to the app at any given time. This design pattern allows other use cases to access user
 * details easily. The class also uses BCrypt for password hashing and checking.
 */
public class User {
    private static Integer userID;
    private static String username;
    private static String password;

    /**
     * Set the user details, effectively logging them in.
     * @param userID            the user's unique identifier (corresponds with the row number in users database)
     * @param username          the user's unique username
     * @param plainPassword     the plain text password that will be hashed and stored
     */
    public static void setUser(Integer userID, String username, String plainPassword) {
        User.userID = userID;
        User.username = username;
        User.password = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    /**
     * A getter for the user's unique identifier.
     *
     * @return the user's unique identifier.
     */
    public static Integer getUserID() {
        return userID;
    }

    /**
     * A getter for the user's unique username.
     *
     * @return the user's unique username.
     */
    public static String getUsername() {
        return username;
    }

    /**
     * A getter for the user's hashed password.
     *
     * @return the user's hashed password.
     */
    public static String getPassword() {
        return password;
    }
}
