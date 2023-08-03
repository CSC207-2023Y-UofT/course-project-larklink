package use_cases_and_adapters.signup_and_login.user_login;

import use_cases_and_adapters.Viewable;

/**
 * A presenter for login use case.
 * This class implements UserLoginOutputBoundary to interact with UserLoginInteractor.
 */
public class UserLoginPresenter implements UserLoginOutputBoundary{

    private Viewable view;

    /**
     * Displays the JoinOrHostView passing in the specified user ID.
     */
    @Override
    public void prepareJoinOrHostView() {
        view.prepareGUI(null);
    }

    /**
     * Displays the view (popup window) indicating invalid login with non-existing username.
     */
    @Override
    public void prepareInvalidUsernameView() {
        view.displayPopUpMessage("Username does not exist.");
    }

    /**
     * Displays the view (popup window) indicating invalid login with incorrect password.
     */
    @Override
    public void prepareInvalidPasswordView() {
        view.displayPopUpMessage("Password doesn't match username.");
    }

    /**
     * Sets the view this presenter displays next.
     *
     * @param view a view set for the next display (in this case JoinOrHostView)
     */
    public void setView(Viewable view) {
        this.view = view;
    }
}
