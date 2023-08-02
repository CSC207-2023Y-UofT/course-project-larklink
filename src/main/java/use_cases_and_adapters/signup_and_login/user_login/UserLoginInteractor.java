package use_cases_and_adapters.signup_and_login.user_login;
import use_cases_and_adapters.UserDBModel;
import entities.User;
import use_cases_and_adapters.signup_and_login.UserDBGateway;
import use_cases_and_adapters.signup_and_login.UserModel;

import java.util.List;

/**
 * An interactor for login use case.
 * This class implements UserLoginInputBoundary to interact with input from the user.
 */
public class UserLoginInteractor implements UserLoginInputBoundary {
    private final UserLoginOutputBoundary presenter;
    private final UserDBGateway database;

    public UserLoginInteractor(UserDBGateway database, UserLoginOutputBoundary presenter) {
        this.database = database;
        this.presenter = presenter;
    }

    /**
     * Handles the user login request by checking the existing users in the database.
     * If a matching username and password are found, it prepares the JoinOrHostView through the presenter.
     * If the username exists but the password doesn't match, it prepares the view indicating invalid password.
     * If the username is not found, it prepares the view indicating username does not exist.
     *
     * @param request the user model containing the username and password of our requesting user
     */
    @Override
    public void handleUserLogin(UserModel request) {
        List<UserDBModel> existingUsers = database.getUsers();

        for (UserDBModel existingUser : existingUsers) {
            if (existingUser.getUsername().equals(request.getUsername())) {
                if (User.checkPassword(request.getPassword(), existingUser.getPassword())) {
                    User.setUser((existingUser.getUserID()), request.getUsername(), request.getPassword());
                    presenter.prepareJoinOrHostView();
                } else {
                    presenter.prepareInvalidPasswordView(); // reject the invalid login with incorrect password
                }
                return; // return here so that prepareInvalidUsernameView is not called in this case
            }
        }
        presenter.prepareInvalidUsernameView(); // reject the invalid login with non-existing username
    }
}