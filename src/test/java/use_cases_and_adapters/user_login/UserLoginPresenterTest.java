package use_cases_and_adapters.user_login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import use_cases_and_adapters.Viewable;
import use_cases_and_adapters.signup_and_login.user_login.UserLoginPresenter;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

/**
 * Test class for UserLoginPresenter.
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
     * Tests prepareJoinOrHostView method.
     */
    @Test
    void testPrepareJoinOrHostView() {
        presenter.prepareJoinOrHostView();

        // checks that mockView calls prepareGUI method exactly once
        verify(mockView, times(1)).prepareGUI();
    }

    // omitted testing the prepareInvalidUsernameView and prepareInvalidPasswordView method
    // because they rely on a static method from Swing

    // omitted testing the setView method because it is just a setter
}
