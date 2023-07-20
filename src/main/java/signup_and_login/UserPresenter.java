package signup_and_login;

import javax.swing.*;

public class UserPresenter implements UserOutputBoundary {

    /**
     * Displays the JoinOrHostView passing in the specified user ID.
     * @param userID the ID of the user
     */
    @Override
    public void prepareJoinOrHostView(String userID) {
        JOptionPane.showMessageDialog(null, userID + " would go to JoinOrHostView now.");
        // pass in the userID here.
    }

    /**
     * Displays the view (popup window) indicating invalid login credentials.
     */
    @Override
    public void prepareInvalidCredentialsView() {
        JOptionPane.showMessageDialog(null, "Password doesn't match existing Username.");
    }
}
