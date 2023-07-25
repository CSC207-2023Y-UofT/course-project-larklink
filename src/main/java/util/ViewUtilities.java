package util;

import database.RoomDBAccess;
import database.RoomDBGateway;
import host_room.HostRoomController;
import host_room.HostRoomInteractor;
import ui.HostRoomPresenter;
import ui.JoinOrHostView;

public class ViewUtilities {

    private final int userID;
    private final RoomDBGateway roomDBAccess;

    public ViewUtilities(RoomDBGateway roomDBAccess, int userID) {
        this.userID = userID;
        this.roomDBAccess = roomDBAccess;
    }
    public void prepareJoinOrHostView() {
        HostRoomPresenter hostRoomPresenter = new HostRoomPresenter();
        HostRoomInteractor hostRoomInteractor = new HostRoomInteractor(roomDBAccess, hostRoomPresenter);
        HostRoomController hostRoomController = new HostRoomController(hostRoomInteractor);
        JoinOrHostView joinOrHostView = new JoinOrHostView(hostRoomController, userID);
        joinOrHostView.prepareGUI();
    }
}
