import database.*;
import database.converters.RoomConverter;
import database.converters.UserConverter;
import use_cases_and_adapters.join_room.JoinRoomController;
import use_cases_and_adapters.join_room.JoinRoomInteractor;
import use_cases_and_adapters.join_room.JoinRoomPresenter;
import views.*;
import use_cases_and_adapters.host_room.HostRoomController;
import use_cases_and_adapters.host_room.HostRoomInteractor;
import use_cases_and_adapters.host_room.HostRoomPresenter;
import use_cases_and_adapters.leave_room.LeaveRoomController;
import use_cases_and_adapters.leave_room.LeaveRoomInteractor;
import use_cases_and_adapters.leave_room.LeaveRoomPresenter;
import use_cases_and_adapters.messaging.MessageController;
import use_cases_and_adapters.messaging.MessageInteractor;
import use_cases_and_adapters.messaging.MessagePresenter;
import use_cases_and_adapters.signup_and_login.UserController;
import use_cases_and_adapters.signup_and_login.UserInteractor;
import use_cases_and_adapters.signup_and_login.UserPresenter;

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

        JoinRoomPresenter joinRoomPresenter = new JoinRoomPresenter();
        JoinRoomInteractor joinRoomInteractor = new JoinRoomInteractor(roomDBAccess, joinRoomPresenter);
        JoinRoomController joinRoomController = new JoinRoomController(joinRoomInteractor);
        JoinOrHostView joinOrHostView = new JoinOrHostView(hostRoomController, joinRoomController);

        joinRoomPresenter.setView(roomView);
        userPresenter.setView(joinOrHostView);
        hostRoomPresenter.setView(roomView);
        leaveRoomPresenter.setView(joinOrHostView);
        sendMessagePresenter.setView(roomView);

        welcomeView.prepareGUI(); // launch app
    }
}
