package use_cases_and_adapters.user_login;

import org.junit.jupiter.api.*;
import org.mockito.*;
import use_cases_and_adapters.Viewable;
import use_cases_and_adapters.signup_and_login.user_login.UserLoginPresenter;

import static org.mockito.MockitoAnnotations.openMocks;

/**
 * This class tests UserLoginPresenter.
 */
public class UserLoginPresenterTest {
    private UserLoginPresenter presenter;

    @Mock
    private Viewable mockView;

    @BeforeEach
    void setUp() {
        openMocks(this);
        presenter = new UserLoginPresenter();
        presenter.setView(mockView);
    }

    /**
     * Tests prepareJoinOrHostView.
     */
    @Test
    void testPrepareJoinOrHostView() {
        presenter.prepareJoinOrHostView();

        // checks that mockView calls prepareGUI exactly once
        Mockito.verify(mockView, Mockito.times(1)).prepareGUI(null);
    }

    @Test
    void testPrepareInvalidUsernameView() {
        presenter.prepareInvalidUsernameView();
        Mockito.verify(mockView, Mockito.times(1)).displayPopUpMessage(
                "Username does not exist."); // check that prepareGUI was called
    }

    @Test
    void testPrepareInvalidPasswordView() {
        presenter.prepareInvalidPasswordView();
        Mockito.verify(mockView, Mockito.times(1)).displayPopUpMessage(
                "Password doesn't match username."); // check that prepareGUI was called
    }
}
