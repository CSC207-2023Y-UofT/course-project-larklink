import database.*;
import database.converters.RoomConverter;
import database.converters.UserConverter;
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

        RoomDBAccess roomDBAccess = new RoomDBAccess(httpClient, roomConverter);
        UserDBAccess userDBAccess = new UserDBAccess(httpClient, userConverter);

        UserPresenter userPresenter = new UserPresenter();
        UserInteractor userInteractor = new UserInteractor(userPresenter, userDBAccess);
        UserController userController = new UserController(userInteractor);
        WelcomeView welcomeView = new WelcomeView(userController);

        LeaveRoomPresenter leaveRoomPresenter = new LeaveRoomPresenter();
        LeaveRoomInteractor leaveRoomInteractor = new LeaveRoomInteractor(roomDBAccess, leaveRoomPresenter);
        LeaveRoomController leaveRoomController = new LeaveRoomController(leaveRoomInteractor);
        MessagePresenter sendMessagePresenter = new MessagePresenter();
        MessageInteractor interactor = new MessageInteractor(roomDBAccess, sendMessagePresenter, larkSoundFilePath);
        MessageController sendMessageController = new MessageController(interactor);
        RoomView roomView = new RoomView(leaveRoomController, sendMessageController);

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
        sendMessagePresenter.setView(roomView);

        welcomeView.prepareGUI(); // launch app
    }
}
