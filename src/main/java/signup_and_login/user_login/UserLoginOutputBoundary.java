package signup_and_login.user_login;

public interface UserLoginOutputBoundary {
    void prepareJoinOrHostView();
    void prepareInvalidUsernameView();
    void prepareInvalidPasswordView();
}
