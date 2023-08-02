package use_cases_and_adapters.signup_and_login.user_login;

/**
 * An output boundary for login use case.
 * This class is an abstraction layer between UserLoginInteractor and UserLoginPresenter.
 */
public interface UserLoginOutputBoundary {

    /**
     * Displays the JoinOrHostView passing in the specified user ID.
     */
    void prepareJoinOrHostView();

    /**
     * Displays the view (popup window) indicating invalid login with non-existing username.
     */
    void prepareInvalidUsernameView();

    /**
     * Displays the view (popup window) indicating invalid login with incorrect password.
     */
    void prepareInvalidPasswordView();
}
