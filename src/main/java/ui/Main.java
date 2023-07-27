package ui;

import database.*;
import host_room.*;
import leave_room.*;
import signup_and_login.*;

public class Main {
    private static final String API_URL = "https://api.sheety.co/78ad1edb28469578058ca4c58c3f478b/larklink";
    public static Integer userID;
    public static Integer roomID;

    public static WelcomeView welcomeView;
    public static JoinOrHostView joinOrHostView;
    // public static HostView hostView;
    // public static JoinView joinView;
    public static RoomView roomView;

    public static void main(String[] args) {
        UserDBGateway userDBAccess = new UserDBAccess(API_URL);
        RoomDBGateway roomDBAccess = new RoomDBAccess(API_URL);

        UserPresenter userPresenter = new UserPresenter();
        UserInteractor userInteractor = new UserInteractor(userPresenter, userDBAccess);
        UserController userController = new UserController(userInteractor);
        welcomeView = new WelcomeView(userController);

        LeaveRoomPresenter leaveRoomPresenter = new LeaveRoomPresenter();
        LeaveRoomInteractor leaveRoomInteractor = new LeaveRoomInteractor(roomDBAccess, leaveRoomPresenter);
        LeaveRoomController leaveRoomController = new LeaveRoomController(leaveRoomInteractor);
        roomView = new RoomView(leaveRoomController);

        HostRoomPresenter hostRoomPresenter = new HostRoomPresenter();
        HostRoomInteractor hostRoomInteractor = new HostRoomInteractor(roomDBAccess, hostRoomPresenter);
        HostRoomController hostRoomController = new HostRoomController(hostRoomInteractor);
        joinOrHostView = new JoinOrHostView(hostRoomController);

        // hostView = new HostView(hostRoomController);
        // joinView = new JoinView(userController);
        welcomeView.prepareGUI(); // launch app
    }
}
