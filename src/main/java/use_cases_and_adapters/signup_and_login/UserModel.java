package use_cases_and_adapters.signup_and_login;

/**
 * This class encapsulates username and password inputs from user.
 */
public class UserModel {
    private final String username;
    private final String password;


    /**
     * Constructs a UserModel with the given username and password from users.
     * @param username the username entered by the user
     * @param password the password entered by the user
     */
    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Getter for username entered by the user
     *
     * @return username entered by the user
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Getter for password entered by the user
     *
     * @return password entered by the user
     */
    public String getPassword() {
        return this.password;
    }
}