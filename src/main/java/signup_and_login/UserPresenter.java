package signup_and_login;

public class UserPresenter implements UserOutputBoundary {

    @Override
    public void prepareJoinOrHostView(UserResponseModel response) {
        System.out.println("goes to join or host view");
    }

    @Override
    public void prepareInvalidCredentialsView(UserResponseModel response) {
        System.out.println("goes to invalid credentials view");
    }
}
