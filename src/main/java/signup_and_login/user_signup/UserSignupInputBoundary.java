package signup_and_login.user_signup;

import signup_and_login.UserModel;

public interface UserSignupInputBoundary {
    void handleUserSignup(UserModel request);
}
