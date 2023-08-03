package use_cases_and_adapters;

/**
 * This class encapsulates user data from database.
 */
public class UserDBModel {
    private final int userID;
    private final String username;
    private final String password;

    /**
     * Constructs UserDBModel object.
     * @param userID user's ID
     * @param username user's name
     * @param password user's password
     */
    public UserDBModel(int userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    public int getUserID() {
        return this.userID;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}