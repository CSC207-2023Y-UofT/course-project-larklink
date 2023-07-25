package ui;

import host_room.HostRoomController;
import host_room.HostRoomInteractor;
import host_room.HostRoomPresenter;
import database.RoomDBGateway;
import signup_and_login.UserOutputBoundary;

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
        HostRoomPresenter hostRoomPresenter = new HostRoomPresenter();
        HostRoomInteractor hostRoomInteractor = new HostRoomInteractor(roomDBAccess, hostRoomPresenter);
        HostRoomController hostRoomController = new HostRoomController(hostRoomInteractor);
        JoinOrHostView joinOrHostView = new JoinOrHostView(hostRoomController, currUserID);
        joinOrHostView.prepareGUI();
    }

    /**
     * Displays the view (popup window) indicating invalid login credentials.
     */
    @Override
    public void prepareInvalidCredentialsView() {
        JOptionPane.showMessageDialog(null, "Password doesn't match existing Username.");
    }
}
