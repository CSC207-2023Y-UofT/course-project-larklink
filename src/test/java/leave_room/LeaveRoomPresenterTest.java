package leave_room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import views.View;
import use_cases_and_adapters.leave_room.LeaveRoomPresenter;

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
        verify(mockView, times(1)).prepareGUI(); // check that prepareGUI was called
    }
}
