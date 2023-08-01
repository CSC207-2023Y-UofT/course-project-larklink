package signup_and_login.user_login;

import signup_and_login.UserModel;

public interface UserLoginInputBoundary {
    void handleUserLogin(UserModel request);
}
