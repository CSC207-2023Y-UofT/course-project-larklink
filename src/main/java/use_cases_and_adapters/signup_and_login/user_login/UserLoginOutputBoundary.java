package use_cases_and_adapters.signup_and_login.user_login;

/**
 * An output boundary for login use case.
 * This class is an abstraction layer between UserLoginInteractor and UserLoginPresenter.
 */
public interface UserLoginOutputBoundary {
    void prepareJoinOrHostView();
    void prepareInvalidUsernameView();
    void prepareInvalidPasswordView();
}
