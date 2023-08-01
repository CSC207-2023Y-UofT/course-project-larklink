import database.*;
import database.converters.RoomConverter;
import database.converters.UserConverter;
import views.*;
import use_cases.host_room.HostRoomController;
import use_cases.host_room.HostRoomInteractor;
import use_cases.host_room.HostRoomPresenter;
import use_cases.join_room.JoinByIDController;
import use_cases.join_room.JoinByIDInteractor;
import use_cases.join_room.JoinByIDPresenter;
import use_cases.leave_room.LeaveRoomController;
import use_cases.leave_room.LeaveRoomInteractor;
import use_cases.leave_room.LeaveRoomPresenter;
import use_cases.messaging.MessageController;
import use_cases.messaging.MessageInteractor;
import use_cases.messaging.MessagePresenter;
import use_cases.signup_and_login.UserController;
import use_cases.signup_and_login.UserInteractor;
import use_cases.signup_and_login.UserPresenter;

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
