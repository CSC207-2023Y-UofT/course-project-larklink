import database.*;
import host_room.*;
import join_room.*;
import leave_room.*;
import messaging.*;
import signup_and_login.*;
import ui.*;

public class Main {
    private static final String API_URL = "https://api.sheety.co/78ad1edb28469578058ca4c58c3f478b/larklink";
    private static final String larkSoundFilePath = "/src/main/assets/lark_sound.wav";
    public static void main(String[] args) {
        HttpClient httpClient = new HttpClient(API_URL);
        RoomConverter roomConverter = new RoomConverter();
        UserConverter userConverter = new UserConverter();


        UserPresenter userPresenter = new UserPresenter();
        UserDBAccess userDBAccess = new UserDBAccess(httpClient, userConverter);
        UserInteractor userInteractor = new UserInteractor(userPresenter, userDBAccess);
        UserController userController = new UserController(userInteractor);
        WelcomeView welcomeView = new WelcomeView(userController);

        LeaveRoomPresenter leaveRoomPresenter = new LeaveRoomPresenter();
        LeaveRoomDBAccess leaveRoomDBAccess = new LeaveRoomDBAccess(httpClient, roomConverter);
        LeaveRoomInteractor leaveRoomInteractor = new LeaveRoomInteractor(leaveRoomDBAccess, leaveRoomPresenter);
        LeaveRoomController leaveRoomController = new LeaveRoomController(leaveRoomInteractor);
        MessagePresenter sendMessagePresenter = new MessagePresenter();
        MessageDBAccess messageDBAccess = new MessageDBAccess(httpClient, roomConverter);
        MessageInteractor interactor = new MessageInteractor(messageDBAccess, sendMessagePresenter, larkSoundFilePath);
        MessageController sendMessageController = new MessageController(interactor);
        RoomView roomView = new RoomView(leaveRoomController, sendMessageController);

        HostRoomPresenter hostRoomPresenter = new HostRoomPresenter();
        HostRoomDBAccess hostRoomDBAccess = new HostRoomDBAccess(httpClient, roomConverter);
        HostRoomInteractor hostRoomInteractor = new HostRoomInteractor(hostRoomDBAccess, hostRoomPresenter);
        HostRoomController hostRoomController = new HostRoomController(hostRoomInteractor);

        JoinByIDPresenter joinByIDPresenter = new JoinByIDPresenter();
        JoinRoomDBAccess joinRoomDBAccess = new JoinRoomDBAccess(httpClient, roomConverter);
        JoinByIDInteractor joinByIDInteractor = new JoinByIDInteractor(joinRoomDBAccess, joinByIDPresenter);
        JoinByIDController joinByIDController = new JoinByIDController(joinByIDInteractor);
        JoinOrHostView joinOrHostView = new JoinOrHostView(hostRoomController, joinByIDController);

        joinByIDPresenter.setView(roomView);
        userPresenter.setView(joinOrHostView);
        hostRoomPresenter.setView(roomView);
        leaveRoomPresenter.setView(joinOrHostView);
        sendMessagePresenter.setView(roomView);

        welcomeView.prepareGUI(); // launch app
    }
}
