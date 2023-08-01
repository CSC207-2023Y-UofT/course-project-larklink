package use_cases.signup_and_login;

public interface UserOutputBoundary {
    void prepareJoinOrHostView();
    void prepareInvalidCredentialsView();
}
