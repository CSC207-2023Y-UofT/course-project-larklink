package leave_room;

import entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ui.View;

import static org.mockito.Mockito.*;

class LeaveRoomPresenterTest {
    private LeaveRoomPresenter leaveRoomPresenter;
    private View mockView;

    @BeforeEach
    void setUp() {
        mockView = Mockito.mock(View.class);
        leaveRoomPresenter = new LeaveRoomPresenter();
        leaveRoomPresenter.setView(mockView);
    }

    @Test
    void testPrepareJoinOrHostView() {
        int userID = 1;
        leaveRoomPresenter.prepareJoinOrHostView();
        assert(User.getUserID() == userID);
        verify(mockView, times(1)).prepareGUI();
    }
}
