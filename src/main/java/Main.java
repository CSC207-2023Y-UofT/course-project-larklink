import signup_and_login.*;

public class Main {
    private static final String API_URL = "https://api.sheety.co/78ad1edb28469578058ca4c58c3f478b/larklink";
    public static void main(String[] args) {
        UserDBGateway userDBAccess = new UserDBAccess(API_URL);
        UserPresenter userPresenter = new UserPresenter();
        UserInteractor userInteractor = new UserInteractor(userPresenter, userDBAccess);
        UserController userController = new UserController(userInteractor);
        WelcomeView welcomeView = new WelcomeView(userController);
        welcomeView.prepareGUI();
    }
}