package util;

import database.RoomDBGateway;
import database.UserDBGateway;
import host_room.HostRoomController;
import host_room.HostRoomInteractor;
import join_room.JoinByIDController;
import join_room.JoinByIDInteractor;
import ui.JoinByIDPresenter;
import ui.HostRoomPresenter;
import ui.JoinOrHostView;

public class ViewUtilities {

    private final Integer userID;
    private final RoomDBGateway roomDBAccess;
    private final UserDBGateway userDBAccess;

    public ViewUtilities(RoomDBGateway roomDBAccess, UserDBGateway userDBAccess, Integer userID) {
        this.userID = userID;
        this.roomDBAccess = roomDBAccess;
        this.userDBAccess = userDBAccess;
    }
    public void prepareJoinOrHostView() {
        HostRoomPresenter hostRoomPresenter = new HostRoomPresenter();
        HostRoomInteractor hostRoomInteractor = new HostRoomInteractor(roomDBAccess, hostRoomPresenter);
        HostRoomController hostRoomController = new HostRoomController(hostRoomInteractor);
        JoinByIDPresenter joinByIDPresenter = new JoinByIDPresenter();
        JoinByIDInteractor joinByIDInteractor = new JoinByIDInteractor(roomDBAccess, userDBAccess, joinByIDPresenter);
        JoinByIDController joinByIDController = new JoinByIDController(joinByIDInteractor);
        JoinOrHostView joinOrHostView = new JoinOrHostView(joinByIDController, hostRoomController, userID);
        joinOrHostView.prepareGUI();
    }


}
