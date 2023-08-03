package use_cases_and_adapters.user_signup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import use_cases_and_adapters.Viewable;
import use_cases_and_adapters.signup_and_login.user_signup.UserSignupPresenter;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

/**
 * This class tests UserSignupPresenter.
 */
class UserSignupPresenterTest {
    private UserSignupPresenter presenter;

    @Mock
    private Viewable mockView;

    @BeforeEach
    void setUp() {
        openMocks(this);
        presenter = new UserSignupPresenter();
        presenter.setView(mockView);
    }

    /**
     * Tests prepareJoinOrHostView.
     */
    @Test
    void testPrepareJoinOrHostView() {
        presenter.prepareJoinOrHostView();
        // checks that mockView calls prepareGUI exactly once
        verify(mockView, times(1)).prepareGUI(null);
    }

    @Test
    void testPrepareUsernameExistsView() {
        presenter.prepareUsernameExistsView();
        verify(mockView, times(1)).displayPopUpMessage(
                "Username already exists. Please try a different name."); // check that prepareGUI was called
    }
}
