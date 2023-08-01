package use_cases_and_adapters.signup_and_login;

public interface UserOutputBoundary {
    void prepareJoinOrHostView();
    void prepareInvalidCredentialsView();
}
