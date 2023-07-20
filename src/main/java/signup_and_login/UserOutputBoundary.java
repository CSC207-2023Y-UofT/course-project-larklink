package signup_and_login;

public interface UserOutputBoundary {
    void prepareJoinOrHostView(String userID);
    void prepareInvalidCredentialsView(String userID);
}
