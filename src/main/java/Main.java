import database.*;
import host_room.*;
import join_room.JoinByIDController;
import join_room.JoinByIDInteractor;
import join_room.JoinByIDPresenter;
import leave_room.*;
import messaging.*;
import signup_and_login.user_login.UserLoginController;
import signup_and_login.user_login.UserLoginInteractor;
import signup_and_login.user_login.UserLoginPresenter;
import ui.*;
import signup_and_login.user_signup.UserSignupController;
import signup_and_login.user_signup.UserSignupInteractor;
import signup_and_login.user_signup.UserSignupPresenter;

public class Main {
    private static final String API_URL = "https://api.sheety.co/78ad1edb28469578058ca4c58c3f478b/larklink";
    private static final String larkSoundFilePath = "/src/main/assets/lark_sound.wav";
    public static void main(String[] args) {
        UserDBAccess userDBAccess = new UserDBAccess(API_URL);
        RoomDBAccess roomDBAccess = new RoomDBAccess(API_URL);

        UserSignupPresenter userSignupPresenter = new UserSignupPresenter();
        UserSignupInteractor userInteractor = new UserSignupInteractor(userSignupPresenter, userDBAccess);
        UserSignupController userSignupController = new UserSignupController(userInteractor);

        UserLoginPresenter userLoginPresenter = new UserLoginPresenter();
        UserLoginInteractor userLoginInteractor = new UserLoginInteractor(userLoginPresenter, userDBAccess);
        UserLoginController userLoginController = new UserLoginController(userLoginInteractor);
        WelcomeView welcomeView = new WelcomeView(userLoginController, userSignupController);

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
        userLoginPresenter.setView(joinOrHostView);
        userSignupPresenter.setView(joinOrHostView);
        hostRoomPresenter.setView(roomView);
        leaveRoomPresenter.setView(joinOrHostView);
        sendMessagePresenter.setView(roomView);

        welcomeView.prepareGUI(); // launch app
    }
}
