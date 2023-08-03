import database_and_drivers.*;
import views.*;
import database_and_drivers.converters.RoomConverter;
import database_and_drivers.converters.UserConverter;
import use_cases_and_adapters.host_room.*;
import use_cases_and_adapters.join_room.*;
import use_cases_and_adapters.leave_room.*;
import use_cases_and_adapters.messaging.*;
import use_cases_and_adapters.signup_and_login.user_login.*;
import use_cases_and_adapters.signup_and_login.user_signup.*;

public class Main {
    private static final String API_URL = "https://api.sheety.co/78ad1edb28469578058ca4c58c3f478b/larklink";
    private static final String larkSoundFilePath = "/src/main/assets/lark_sound.wav";
    public static void main(String[] args) {
        HttpClient httpClient = new HttpClient(API_URL);
        LarkSoundPlayer larkSoundPlayer = new LarkSoundPlayer(larkSoundFilePath);

        RoomConverter roomConverter = new RoomConverter();
        UserConverter userConverter = new UserConverter();

        RoomDBAccess roomDBAccess = new RoomDBAccess(httpClient, roomConverter);
        UserDBAccess userDBAccess = new UserDBAccess(httpClient, userConverter);

        UserSignupPresenter userSignupPresenter = new UserSignupPresenter();
        UserSignupInteractor userSignupInteractor = new UserSignupInteractor(userDBAccess, userSignupPresenter);
        UserSignupController userSignupController = new UserSignupController(userSignupInteractor);

        UserLoginPresenter userLoginPresenter = new UserLoginPresenter();
        UserLoginInteractor userLoginInteractor = new UserLoginInteractor(userDBAccess, userLoginPresenter);
        UserLoginController userLoginController = new UserLoginController(userLoginInteractor);
        WelcomeView welcomeView = new WelcomeView(userLoginController, userSignupController);

        LeaveRoomPresenter leaveRoomPresenter = new LeaveRoomPresenter();
        LeaveRoomInteractor leaveRoomInteractor = new LeaveRoomInteractor(roomDBAccess, leaveRoomPresenter);
        LeaveRoomController leaveRoomController = new LeaveRoomController(leaveRoomInteractor);

        MessagePresenter sendMessagePresenter = new MessagePresenter();
        MessageInteractor interactor = new MessageInteractor(roomDBAccess, sendMessagePresenter, larkSoundPlayer);
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
        userLoginPresenter.setView(joinOrHostView);
        userSignupPresenter.setView(joinOrHostView);
        hostRoomPresenter.setView(roomView);
        leaveRoomPresenter.setView(joinOrHostView);
        sendMessagePresenter.setView(roomView);

        welcomeView.prepareGUI(); // launch app
    }
}
