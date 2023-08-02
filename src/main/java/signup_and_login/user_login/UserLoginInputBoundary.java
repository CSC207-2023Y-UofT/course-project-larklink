package signup_and_login.user_login;

import signup_and_login.UserModel;

/**
 * An input boundary for login use case.
 * This class is an abstraction layer between UserLoginController and UserLoginInteractor.
 */
public interface UserLoginInputBoundary {
    void handleUserLogin(UserModel request);
}
