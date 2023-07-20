package signup_and_login;

public class UserController {
    private UserInputBoundary inputBoundary;

    public UserController(UserInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void formatAndHandleUser(String username, String password) {
        UserRequestModel request = new UserRequestModel(username, password);
        inputBoundary.handleUser(request);
    }
}