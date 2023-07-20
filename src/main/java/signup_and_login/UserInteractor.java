package signup_and_login;
import entities.User;

import java.util.List;

public class UserInteractor implements UserInputBoundary {
    private final UserOutputBoundary presenter;
    private final UserDBGateway database;

    public UserInteractor(UserOutputBoundary presenter, UserDBGateway database) {
        this.presenter = presenter;
        this.database = database;
    }

    @Override
    public void handleUser(UserRequestModel request) {
        List<UserRequestModel> existingUsers = database.loadUsers();

        for (UserRequestModel existingUser : existingUsers) {
            if (existingUser.getUsername().equals(request.getUsername())) {
                if (existingUser.getPassword().equals(request.getPassword())) {
                    presenter.prepareJoinOrHostView(request.getUsername());
                } else {
                    presenter.prepareInvalidCredentialsView(request.getUsername());
                }
                return; // found existing user, no need to check further
            }
        }
        // no existing user found, so we save a new user
        database.SaveNewUser(request);
        presenter.prepareJoinOrHostView(request.getUsername());
    }
}