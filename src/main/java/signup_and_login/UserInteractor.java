package signup_and_login;
import database.UserDBGateway;
import models.UserDBModel;
import entities.User;
import models.UserModel;

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
     * @param request the user model containing the username and password of our requesting user
     */
    @Override
    public void handleUser(UserModel request) {
        List<UserDBModel> existingUsers = database.loadUsers();

        for (UserDBModel existingUser : existingUsers) {
            if (existingUser.getUsername().equals(request.getUsername())) {
                if (User.checkPassword(request.getPassword(), existingUser.getPassword())) {
                    presenter.prepareJoinOrHostView(existingUser.getUserID()); // "log in" the existing user
                } else {
                    presenter.prepareInvalidCredentialsView();
                }
                return; // found existing user, no need to check further
            }
        }
        // no existing user found, so we hash their password and set their userID to save the new user
        UserDBModel newUser = new UserDBModel(
                (existingUsers.size() + 2),
                request.getUsername(),
                User.hashPassword(request.getPassword()));
        database.saveNewUser(newUser);
        presenter.prepareJoinOrHostView(newUser.getUserID());
    }
}