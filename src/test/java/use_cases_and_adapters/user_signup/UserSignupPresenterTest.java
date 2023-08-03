package use_cases_and_adapters.user_signup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import use_cases_and_adapters.Viewable;
import use_cases_and_adapters.signup_and_login.user_signup.UserSignupPresenter;

import static org.mockito.Mockito.*;

class UserSignupPresenterTest {
    private UserSignupPresenter userRegisterPresenter;
    private Viewable mockView;

    @BeforeEach
    void setUp() {
        mockView = Mockito.mock(Viewable.class);
        userRegisterPresenter = new UserSignupPresenter();
        userRegisterPresenter.setView(mockView);
    }

    @Test
    void testPrepareJoinOrHostView() {
        userRegisterPresenter.prepareJoinOrHostView();
        verify(mockView, times(1)).prepareGUI(); // check that prepareGUI was called
    }

    // omitted testing the prepareInvalidCredentialsView method because it relies on a static method from Swing
    // omitted testing the setView method because it is just a setter
}
