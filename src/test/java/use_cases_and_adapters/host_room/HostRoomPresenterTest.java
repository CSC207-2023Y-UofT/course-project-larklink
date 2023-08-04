package use_cases_and_adapters.host_room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_cases_and_adapters.leave_room.LeaveRoomPresenter;
import views.View;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class HostRoomPresenterTest {
    private HostRoomPresenter hostRoomPresenter;
    private View mockView;

    @BeforeEach
    void setUp() {
        mockView = Mockito.mock(View.class);
        hostRoomPresenter = new HostRoomPresenter();
        hostRoomPresenter.setView(mockView);
    }

    @Test
    void testPrepareJoinOrHostView() {
        hostRoomPresenter.prepareRoomView("");
        verify(mockView, times(1)).prepareGUI(""); // check that prepareGUI was called
    }
}
