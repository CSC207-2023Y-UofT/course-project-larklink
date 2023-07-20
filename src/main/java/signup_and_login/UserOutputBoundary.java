package signup_and_login;

public interface UserOutputBoundary {
    void prepareJoinOrHostView(UserResponseModel response);
    void prepareInvalidCredentialsView(UserResponseModel response);
}
