package signup_and_login;
import java.util.List;

public class UserInteractor implements UserInputBoundary {
    private final UserOutputBoundary presenter;
    private final UserDBGateway database;

    public UserInteractor(UserOutputBoundary presenter, UserDBGateway database) {
        this.presenter = presenter;
        this.database = database;
    }

    /**
     * Handles the user request by checking the existing users in the database.
     * If a matching username and password are found, it prepares the JoinOrHostView through the presenter.
     * If the username exists but the password doesn't match, it prepares the view indicating invalid credentials.
     * If the username is not found, it saves a new user to the database and prepares the JoinOrHostView.
     *
     * @param request the user request model containing the username and password
     */
    @Override
    public void handleUser(UserRequestModel request) {
        List<UserRequestModel> existingUsers = database.loadUsers();

        for (UserRequestModel existingUser : existingUsers) {
            if (existingUser.getUsername().equals(request.getUsername())) {
                if (existingUser.getPassword().equals(request.getPassword())) {
                    presenter.prepareJoinOrHostView(request.getUsername()); // "log in" the user
                } else {
                    presenter.prepareInvalidCredentialsView();
                }
                return; // found existing user, no need to check further
            }
        }
        // no existing user found, so we save a new user
        database.saveNewUser(request);
        presenter.prepareJoinOrHostView(request.getUsername());
    }
}