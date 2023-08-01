package signup_and_login.user_login;

import ui.View;

import javax.swing.*;

public class UserLoginPresenter implements UserLoginOutputBoundary{

    private View view;

    /**
     * Displays the JoinOrHostView passing in the specified user ID.
     */
    @Override
    public void prepareJoinOrHostView() {
        view.prepareGUI();
    }

    /**
     * Displays the view (popup window) indicating invalid login with non-existing username.
     */
    @Override
    public void prepareInvalidUsernameView() {
        JOptionPane.showMessageDialog(null, "Username does not exist.");
    }

    /**
     * Displays the view (popup window) indicating invalid login with incorrect password.
     */
    @Override
    public void prepareInvalidPasswordView() {
        JOptionPane.showMessageDialog(null, "Password doesn't match existing Username.");
    }

    /**
     * Sets the view this presenter displays next (in this case JoinOrHostView).
     */
    public void setView(View view) {
        this.view = view;
    }
}
