package signup_and_login;

public interface UserOutputBoundary {
    UserResponseModel prepareSignedInView();
    UserResponseModel prepareSignedUpView();
    UserResponseModel prepareInvalidCredentialsView();
}
