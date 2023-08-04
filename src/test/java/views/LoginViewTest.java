package views;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import use_cases_and_adapters.signup_and_login.UserModel;
import use_cases_and_adapters.signup_and_login.user_login.UserLoginController;
import use_cases_and_adapters.signup_and_login.user_login.UserLoginInputBoundary;
import use_cases_and_adapters.signup_and_login.user_signup.UserSignupController;
import use_cases_and_adapters.signup_and_login.user_signup.UserSignupInputBoundary;

import java.awt.*;
import java.awt.event.InputEvent;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class LoginViewTest {
    private UserLoginController loginController;
    private UserSignupController signupController;
    @Mock
    private UserLoginInputBoundary logInInputBoundary;

    @Mock
    private UserSignupInputBoundary signupInputBoundary;

    @BeforeEach
    void setUp() {
        logInInputBoundary = Mockito.mock(UserLoginInputBoundary.class);
        signupInputBoundary = Mockito.mock(UserSignupInputBoundary.class);
        loginController = new UserLoginController(logInInputBoundary);
        signupController = new UserSignupController(signupInputBoundary);
    }

    @Test
    void testLoginButton() throws AWTException {

        Robot bot = new Robot();
        // launch view
        ViewManager.startWelcomeView(loginController, signupController); // launch app
        bot.mouseMove(828, 435);
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_MASK);


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bot.mouseMove(828, 385);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_MASK);


        // enter in username
        bot.keyPress(80);
        bot.keyPress(80);
        bot.keyPress(80);
        bot.keyPress(80);
        bot.keyPress(80);
        bot.keyPress(80);

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        bot.mouseMove(828, 450);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_MASK);


        // enter password
        bot.keyPress(80);
        bot.keyPress(80);
        bot.keyPress(80);
        bot.keyPress(80);
        bot.keyPress(80);
        bot.keyPress(80);
        bot.keyPress(80);
        bot.keyPress(80);
        bot.keyPress(80);
        bot.keyPress(80);

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bot.mouseMove(828, 500);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_MASK);
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        verify(logInInputBoundary, times(1)).handleUserLogin(any());

    }
}
