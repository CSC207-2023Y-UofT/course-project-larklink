package use_case.signup_and_login;

import models.UserModel;

public interface UserInputBoundary {
    void handleUser(UserModel request);
}
