package use_cases_and_adapters.signup_and_login.user_signup;

import use_cases_and_adapters.signup_and_login.UserModel;

/**
 * An input boundary for signup use case.
 * This class is an abstraction layer between UserSignupController and UserSignupInteractor.
 */
public interface UserSignupInputBoundary {

    /**
     * Handles the user request by checking the existing users in the database.
     * If the username is already in use, it prepares the view indicating username already exists.
     * If the username is not found, it saves a new user to the database and prepares the JoinOrHostView.
     *
     * @param request the user model containing the username and password of our requesting user
     */
    void handleUserSignup(UserModel request);
}
