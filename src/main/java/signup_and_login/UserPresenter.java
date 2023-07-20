package signup_and_login;

import javax.swing.*;

public class UserPresenter implements UserOutputBoundary {

    @Override
    public void prepareJoinOrHostView(String userID) {
        JOptionPane.showMessageDialog(null, userID + " would go to JoinOrHostView now.");
    }

    @Override
    public void prepareInvalidCredentialsView(String userID) {
        JOptionPane.showMessageDialog(null, "Username and Password don't match.");
    }
}
