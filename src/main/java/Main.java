import signup_and_login.*;

public class Main {
    public static void main(String[] args) {
        UserDBGateway userDBAccess = new UserDBAccess();
        UserPresenter userPresenter = new UserPresenter();
        UserInteractor userInteractor = new UserInteractor(userPresenter, userDBAccess);
        UserController userController = new UserController(userInteractor);
        WelcomeView welcomeView = new WelcomeView(userController);
        welcomeView.prepareGUI();
    }
}