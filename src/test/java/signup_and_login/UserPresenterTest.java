package signup_and_login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ui.View;

import static org.mockito.Mockito.*;

class UserPresenterTest {
    private UserPresenter userPresenter;
    private View mockView;

    @BeforeEach
    void setUp() {
        mockView = Mockito.mock(View.class);
        userPresenter = new UserPresenter();
        userPresenter.setView(mockView);
    }

    @Test
    void testPrepareJoinOrHostView() {
        int userID = 1;
        userPresenter.prepareJoinOrHostView(userID);
        assert(View.userID == userID); // check that View's userID was set properly
        verify(mockView, times(1)).prepareGUI(); // check that prepareGUI was called
    }

    // omitted testing the prepareInvalidCredentialsView method because it relies on a static method from Swing
    // omitted testing the setView method because it is just a setter
}