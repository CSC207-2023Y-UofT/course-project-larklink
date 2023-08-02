package use_cases_and_adapters.signup_and_login.user_login;

import use_cases_and_adapters.signup_and_login.UserModel;

/**
 * An input boundary for login use case.
 * This class is an abstraction layer between UserLoginController and UserLoginInteractor.
 */
public interface UserLoginInputBoundary {

    /**
     * Handles the user login request by checking the existing users in the database.
     * If a matching username and password are found, it prepares the JoinOrHostView through the presenter.
     * If the username exists but the password doesn't match, it prepares the view indicating invalid password.
     * If the username is not found, it prepares the view indicating username does not exist.
     *
     * @param request the user model containing the username and password of our requesting user
     */
    void handleUserLogin(UserModel request);
}
