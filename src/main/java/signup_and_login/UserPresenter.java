package signup_and_login;

import javax.swing.*;
import ui.Main;

public class UserPresenter implements UserOutputBoundary {
    /**
     * Displays the JoinOrHostView passing in the specified user ID.
     * @param id the ID of the user
     */
    @Override
    public void prepareJoinOrHostView(int id) {
        Main.userID = id;
        Main.updateViews(ui.Main.State.JOIN_OR_HOST);
    }

    /**
     * Displays the view (popup window) indicating invalid login credentials.
     */
    @Override
    public void prepareInvalidCredentialsView() {
        JOptionPane.showMessageDialog(null, "Password doesn't match existing Username.");
    }
}
