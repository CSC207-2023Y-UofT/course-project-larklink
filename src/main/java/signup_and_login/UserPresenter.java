package signup_and_login;

import javax.swing.*;
import ui.View;

public class UserPresenter implements UserOutputBoundary {
    private View view;

    /**
     * Displays the JoinOrHostView.
     */
    @Override
    public void prepareJoinOrHostView() {
        view.prepareGUI();
    }

    /**
     * Displays the view (popup window) indicating invalid login credentials.
     */
    @Override
    public void prepareInvalidCredentialsView() {
        JOptionPane.showMessageDialog(null, "Password doesn't match existing Username.");
    }

    /**
     * Sets the view this presenter displays next (in this case JoinOrHostView).
     */
    public void setView(View view) {
        this.view = view;
    }
}
