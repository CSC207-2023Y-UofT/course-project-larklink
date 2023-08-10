package use_cases_and_adapters;

/**
 * This class encapsulates user data from database.
 */
public class UserDBModel {
    private final Integer id;
    private final String username;
    private final String password;

    /**
     * Constructs UserDBModel object.
     * @param id The unique identifier of the user
     * @param username The username of the user
     * @param password The hashed password of the user
     */
    public UserDBModel(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    /**
     * A getter for the unique identifier of the user.
     *
     * @return The unique identifier of the user.
     */
    public int getUserID() {
        return this.id;
    }

    /**
     * A getter for the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * A getter for the hashed password of the user.
     *
     * @return The hashed password of the user.
     */
    public String getPassword() {
        return this.password;
    }
}