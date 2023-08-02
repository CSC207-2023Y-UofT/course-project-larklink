package signup_and_login.user_signup;

import signup_and_login.UserModel;

/**
 * A controller for signup use case.
 */
public class UserSignupController {
    private final UserSignupInputBoundary inputBoundary;

    public UserSignupController(UserSignupInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Formats the username and password into a UserModel and delegates the handling to UserSignupInteractor
     * through UserSignupInputBoundary.
     *
     * @param username the username entered by the user
     * @param password the password entered by the user
     */
    public void formatAndHandleUserSignup(String username, String password) {
        UserModel request = new UserModel(username, password);
        inputBoundary.handleUserSignup(request);
    }
}