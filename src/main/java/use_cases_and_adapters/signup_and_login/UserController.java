package use_cases_and_adapters.signup_and_login;

public class UserController {
    private final UserInputBoundary inputBoundary;

    public UserController(UserInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Formats the username and password into a UserRequestModel and delegates the handling to UserInteractor
     * through UserInputBoundary.
     * @param username the username entered by the user
     * @param password the password entered by the user
     */
    public void formatAndHandleUser(String username, String password) {
        UserModel request = new UserModel(username, password);
        inputBoundary.handleUser(request);
    }
}