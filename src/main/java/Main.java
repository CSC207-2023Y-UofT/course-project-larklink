import database.*;
import host_room.*;
import join_room.JoinByIDController;
import join_room.JoinByIDInteractor;
import join_room.JoinByIDPresenter;
import leave_room.*;
import signup_and_login.*;
import ui.*;

public class Main {
    private static final String API_URL = "https://api.sheety.co/78ad1edb28469578058ca4c58c3f478b/larklink";
    public static void main(String[] args) {
        UserDBGateway userDBAccess = new UserDBAccess(API_URL);
        RoomDBGateway roomDBAccess = new RoomDBAccess(API_URL);

        UserPresenter userPresenter = new UserPresenter();
        UserInteractor userInteractor = new UserInteractor(userPresenter, userDBAccess);
        UserController userController = new UserController(userInteractor);
        WelcomeView welcomeView = new WelcomeView(userController);

        LeaveRoomPresenter leaveRoomPresenter = new LeaveRoomPresenter();
        LeaveRoomInteractor leaveRoomInteractor = new LeaveRoomInteractor(roomDBAccess, leaveRoomPresenter);
        LeaveRoomController leaveRoomController = new LeaveRoomController(leaveRoomInteractor);
        RoomView roomView = new RoomView(leaveRoomController);

        HostRoomPresenter hostRoomPresenter = new HostRoomPresenter();
        HostRoomInteractor hostRoomInteractor = new HostRoomInteractor(roomDBAccess, hostRoomPresenter);
        HostRoomController hostRoomController = new HostRoomController(hostRoomInteractor);

        JoinByIDPresenter joinByIDPresenter = new JoinByIDPresenter();
        JoinByIDInteractor joinByIDInteractor = new JoinByIDInteractor(roomDBAccess, joinByIDPresenter);
        JoinByIDController joinByIDController = new JoinByIDController(joinByIDInteractor);
        JoinOrHostView joinOrHostView = new JoinOrHostView(hostRoomController, joinByIDController);

        joinByIDPresenter.setView(roomView);
        userPresenter.setView(joinOrHostView);
        hostRoomPresenter.setView(roomView);
        leaveRoomPresenter.setView(joinOrHostView);

        welcomeView.prepareGUI(); // launch app
    }
}
