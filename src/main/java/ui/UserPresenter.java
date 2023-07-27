package ui;

import database.UserDBGateway;
import database.RoomDBGateway;
import signup_and_login.UserOutputBoundary;
import util.ViewUtilities;

import javax.swing.*;

public class UserPresenter implements UserOutputBoundary {
    private RoomDBGateway roomDBAccess;
    private UserDBGateway userDBAccess;
    public UserPresenter(RoomDBGateway roomDBAccess, UserDBGateway userDBAccess) {
        this.roomDBAccess = roomDBAccess;
        this.userDBAccess = userDBAccess;
    }
    /**
     * Displays the JoinOrHostView passing in the specified user ID.
     * @param userID the ID of the user
     */
    @Override
    public void prepareJoinOrHostView(int userID) {
        ViewUtilities viewUtilities = new ViewUtilities(roomDBAccess, userDBAccess, userID);
        viewUtilities.prepareJoinOrHostView();
    }

    /**
     * Displays the view (popup window) indicating invalid login credentials.
     */
    @Override
    public void prepareInvalidCredentialsView() {
        JOptionPane.showMessageDialog(null, "Password doesn't match existing Username.");
    }
}
