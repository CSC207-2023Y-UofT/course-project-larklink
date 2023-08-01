package signup_and_login.user_signup;

import javax.swing.*;
import ui.View;

public class UserSignupPresenter implements UserSignupOutputBoundary {
    private View view;

    /**
     * Displays the JoinOrHostView passing in the specified user ID.
     */
    @Override
    public void prepareJoinOrHostView() {
        view.prepareGUI();
    }

    /**
     * Displays the view (popup window) indicating username already exists.
     */
    @Override
    public void prepareUsernameExistsView() {
        JOptionPane.showMessageDialog(null, "Username already exists. Please try a different name.");
    }

    /**
     * Sets the view this presenter displays next (in this case JoinOrHostView).
     */
    public void setView(View view) {
        this.view = view;
    }
}
