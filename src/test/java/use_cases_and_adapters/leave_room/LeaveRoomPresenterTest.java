package use_cases_and_adapters.leave_room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import views.View;

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
        leaveRoomPresenter.prepareJoinOrHostView();
        verify(mockView, times(1)).prepareGUI(null); // check that prepareGUI was called
    }

    @Test
    void testPrepareFailedToLeaveRoomView() {
        leaveRoomPresenter.prepareFailedToLeaveRoomView();
        verify(mockView, times(1)).displayPopUpMessage("Failed to leave the room.");
    }
}
