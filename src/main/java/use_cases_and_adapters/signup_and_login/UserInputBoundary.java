package use_cases_and_adapters.signup_and_login;

public interface UserInputBoundary {
    void handleUser(UserModel request);
}
