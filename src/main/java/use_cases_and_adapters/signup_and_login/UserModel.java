package use_cases_and_adapters.signup_and_login;

/**
 * An input model for signup and login use case.
 * This class encapsulates inputs from user.
 */
public class UserModel {
    private final String username;
    private final String password;

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