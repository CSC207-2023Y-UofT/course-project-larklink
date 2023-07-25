package ui;

import host_room.HostRoomController;
import host_room.HostRoomInteractor;
import database.RoomDBGateway;
import signup_and_login.UserOutputBoundary;
import util.ViewUtilities;

import javax.swing.*;

public class UserPresenter implements UserOutputBoundary {
    private RoomDBGateway roomDBAccess;
    private Integer currUserID;
    public UserPresenter(RoomDBGateway roomDBAccess, Integer currUserID) {
        this.roomDBAccess = roomDBAccess;
        this.currUserID = currUserID;
    }
    /**
     * Displays the JoinOrHostView passing in the specified user ID.
     * @param userID the ID of the user
     */
    @Override
    public void prepareJoinOrHostView(int userID) {
        ViewUtilities viewUtilities = new ViewUtilities(roomDBAccess, userID);
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
