package signup_and_login.user_login;
import database.UserDBModel;
import entities.User;
import signup_and_login.UserDBGateway;
import signup_and_login.UserModel;

import java.util.List;

public class UserLoginInteractor implements UserLoginInputBoundary {
    private final UserLoginOutputBoundary presenter;
    private final UserDBGateway database;

    public UserLoginInteractor(UserLoginOutputBoundary presenter, UserDBGateway database) {
        this.presenter = presenter;
        this.database = database;
    }

    /**
     * Handles the user login request by checking the existing users in the database.
     * If a matching username and password are found, it prepares the JoinOrHostView through the presenter.
     * If the username exists but the password doesn't match, it prepares the view indicating invalid password.
     * If the username is not found, it shows it prepares the view indicating invalid username.
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
                return;
            }
        }
        presenter.prepareInvalidUsernameView(); // reject the invalid login with non-existing username
    }
}