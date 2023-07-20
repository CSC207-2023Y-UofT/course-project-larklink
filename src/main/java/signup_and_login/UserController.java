package signup_and_login;

public class UserController {
    private UserInputBoundary inputBoundary;

    public UserController(UserInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public UserResponseModel formatAndHandleUser(String username, String password) {
        UserRequestModel request = new UserRequestModel(username, password);
        return inputBoundary.handleUser(request);
    }
}