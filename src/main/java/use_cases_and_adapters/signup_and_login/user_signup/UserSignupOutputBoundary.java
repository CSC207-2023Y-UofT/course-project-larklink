package use_cases_and_adapters.signup_and_login.user_signup;

/**
 * An output boundary for signup use case.
 * This class is an abstraction layer between UserSignupInteractor and UserSignupPresenter.
 */
public interface UserSignupOutputBoundary {
    void prepareJoinOrHostView();
    void prepareUsernameExistsView();
}
