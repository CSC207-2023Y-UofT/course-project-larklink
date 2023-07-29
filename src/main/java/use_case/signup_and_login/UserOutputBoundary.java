package use_case.signup_and_login;

public interface UserOutputBoundary {
    void prepareJoinOrHostView(int userID);
    void prepareInvalidCredentialsView();
}
