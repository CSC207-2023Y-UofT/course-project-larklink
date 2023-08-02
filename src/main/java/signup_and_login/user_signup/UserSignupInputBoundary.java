package signup_and_login.user_signup;

import signup_and_login.UserModel;

/**
 * An input boundary for signup use case.
 * This class is an abstraction layer between UserSignupController and UserSignupInteractor.
 */
public interface UserSignupInputBoundary {
    void handleUserSignup(UserModel request);
}
