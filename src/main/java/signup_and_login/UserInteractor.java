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
    public UserResponseModel handleUser(UserRequestModel request) {
        List<UserRequestModel> existingUsers = database.loadUsers();

        for (UserRequestModel existingUser : existingUsers) {
            if (existingUser.getUsername().equals(request.getUsername())) {
                if (existingUser.getPassword().equals(request.getPassword())) {
                    return new UserResponseModel(request.getUsername(), true);
                    // presenter.prepareJoinOrHostView(response);
                } else {
                    return new UserResponseModel(request.getUsername(), false);
                    // presenter.prepareInvalidCredentialsView(response);
                }
            }
        }
        // we didn't find any matching user, so we save a new user
        database.SaveNewUser(request);
        return new UserResponseModel(request.getUsername(), true);
        // presenter.prepareJoinOrHostView(response);
    }
}