import database.RoomDBAccess;
import database.RoomDBGateway;
import database.UserDBAccess;
import database.UserDBGateway;
import signup_and_login.*;
import ui.UserPresenter;
import ui.WelcomeView;

public class Main {
    private static final String API_URL = "https://api.sheety.co/78ad1edb28469578058ca4c58c3f478b/larklink";

    private static Integer currUserID = 5;
    public static void main(String[] args) {
        UserDBGateway userDBAccess = new UserDBAccess(API_URL);
        RoomDBGateway roomDBAccess = new RoomDBAccess(API_URL);

        UserPresenter userPresenter = new UserPresenter(roomDBAccess);
        UserInteractor userInteractor = new UserInteractor(userPresenter, userDBAccess);
        UserController userController = new UserController(userInteractor);
        WelcomeView welcomeView = new WelcomeView(userController);
        welcomeView.prepareGUI();



        //LeaveRoomPresenter leaveRoomPresenter = new LeaveRoomPresenter();
        //LeaveRoomInteractor leaveRoomInteractor = new LeaveRoomInteractor(roomDBAccess, leaveRoomPresenter);
        //LeaveRoomController leaveRoomController = new LeaveRoomController(leaveRoomInteractor);
        //RoomView roomView = new RoomView(leaveRoomController, "room1", "user1");
        //roomView.prepareGUI();
    }
}