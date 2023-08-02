package signup_and_login.user_login;

import signup_and_login.UserModel;

/**
 * A controller for login use case.
 */
public class UserLoginController {
    private final UserLoginInputBoundary inputBoundary;

    public UserLoginController(UserLoginInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Formats the username and password into a UserModel and delegates the handling to UserLoginInteractor
     * through UserLoginInputBoundary.
     *
     * @param username the username entered by the user
     * @param password the password entered by the user
     */
    public void formatAndHandleUserLogin(String username, String password) {
        UserModel request = new UserModel(username, password);
        inputBoundary.handleUserLogin(request);
    }
}
