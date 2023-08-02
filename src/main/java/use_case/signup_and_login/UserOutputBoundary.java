package use_case.signup_and_login;

public interface UserOutputBoundary {
    void prepareJoinOrHostView();
    void prepareInvalidCredentialsView();
}
