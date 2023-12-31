package use_cases_and_adapters.signup_and_login.user_signup;

import use_cases_and_adapters.Viewable;

/**
 * A presenter for signup use case.
 * This class implements UserSignupOutputBoundary to interact with UserSignupInteractor.
 */
public class UserSignupPresenter implements UserSignupOutputBoundary {
    private Viewable view;

    /**
     * Displays the JoinOrHostView passing in the specified user ID.
     */
    @Override
    public void prepareJoinOrHostView() {
        view.prepareGUI(null);
    }

    /**
     * Displays the view (popup window) indicating username already exists.
     */
    @Override
    public void prepareUsernameExistsView() {
        view.displayPopUpMessage("Username already exists. Please try a different name.");
    }

    /**
     * Sets the view this presenter displays next
     *
     * @param view a view set for the next display (in this case JoinOrHostView)
     */
    public void setView(Viewable view) {
        this.view = view;
    }
}
