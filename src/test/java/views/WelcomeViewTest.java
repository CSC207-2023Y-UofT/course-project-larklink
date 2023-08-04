package views;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import use_cases_and_adapters.signup_and_login.user_login.UserLoginController;
import use_cases_and_adapters.signup_and_login.user_login.UserLoginInputBoundary;

public class WelcomeViewTest {
    private UserLoginController controller;
    private View mockWelcomeView;
    private UserLoginInputBoundary inputBoundary;

    @BeforeEach
    void setUp() {
        mockWelcomeView = Mockito.mock(WelcomeView.class);
        inputBoundary = Mockito.mock(UserLoginInputBoundary.class);
        controller = new UserLoginController();
    }
}
