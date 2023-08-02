package use_cases_and_adapters.signup_and_login.user_signup;
import use_cases_and_adapters.UserDBModel;
import entities.User;
import use_cases_and_adapters.signup_and_login.UserDBGateway;
import use_cases_and_adapters.signup_and_login.UserModel;

import java.util.List;

/**
 * An interactor for signup use case.
 * This class implements UserSignupInputBoundary to interact with input from the user.
 */
public class UserSignupInteractor implements UserSignupInputBoundary {
    private final UserSignupOutputBoundary presenter;
    private final UserDBGateway database;

    public UserSignupInteractor(UserDBGateway database, UserSignupOutputBoundary presenter) {
        this.database = database;
        this.presenter = presenter;
    }

    /**
     * Handles the user request by checking the existing users in the database.
     * If the username is already in use, it prepares the view indicating username already exists.
     * If the username is not found, it saves a new user to the database and prepares the JoinOrHostView.
     *
     * @param request the user model containing the username and password of our requesting user
     */
    @Override
    public void handleUserSignup(UserModel request) {
        List<UserDBModel> existingUsers = database.getUsers();

        for (UserDBModel existingUser : existingUsers) {
            if (existingUser.getUsername().equals(request.getUsername())) {
                presenter.prepareUsernameExistsView(); // reject the invalid signup with existing username
                return; // return here so that prepareJoinOrHostView is not called in this case
            }
        }
        // no existing user found, so we hash their password and set their userID to save the new user
        User.setUser((existingUsers.size() + 2), request.getUsername(), request.getPassword());
        database.addAUser(new UserDBModel(User.getUserID(), User.getUsername(), User.getPassword()));
        presenter.prepareJoinOrHostView();
    }
}