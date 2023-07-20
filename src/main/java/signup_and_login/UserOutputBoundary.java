package signup_and_login;

public interface UserOutputBoundary {
    void prepareJoinOrHostView(String user);
    void prepareInvalidCredentialsView(String user);
}
