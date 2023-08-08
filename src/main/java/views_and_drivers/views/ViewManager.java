package views_and_drivers.views;

import use_cases_and_adapters.signup_and_login.user_login.UserLoginController;
import use_cases_and_adapters.signup_and_login.user_signup.UserSignupController;

/**
 * This class manages WelcomeView, SignupView, and LoginView.
 * This class provides static methods to create WelcomeView and switch from SignupView/LoginView to WelcomeView.
 */
public class ViewManager {
    protected static WelcomeView welcomeView;

    /**
     * A static method to create and start the welcomeView
     *
     * @param loginController the UserLoginController object
     * @param signupController the UserSignupController object
     */
    public static void startWelcomeView(UserLoginController loginController, UserSignupController signupController) {
        WelcomeView welcomeView = new WelcomeView(loginController, signupController);
        ViewManager.welcomeView = welcomeView;
        welcomeView.prepareGUI(null);
    }

    /**
     * A static method for signupView and LoginView to go back to the welcomeView
     */
    public static void switchToWelcomeView() {
        ViewManager.welcomeView.prepareGUI(null);
    }
}
