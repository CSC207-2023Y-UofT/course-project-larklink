package use_cases_and_adapters.leave_room;

import org.junit.jupiter.api.*;
import org.mockito.*;

import views_and_drivers.views.View;
/**
 * The LeaveRoomPresenterTest class contains unit tests for the LeaveRoomPresenter class.
 * The prepareJoinOrHostView() method is tested while a mock View abstract class is built using Mockito.
 */
class LeaveRoomPresenterTest {
    private LeaveRoomPresenter leaveRoomPresenter;
    private View mockView;
    /**
     * Setup method to initialize the test environment.
     * It builds a mock View abstract class
     * and designates the mockView as the LeaveRoomPresenter's associated View using Mockito
     */
    @BeforeEach
    void setUp() {
        mockView = Mockito.mock(View.class);
        leaveRoomPresenter = new LeaveRoomPresenter();
        leaveRoomPresenter.setView(mockView);
    }
    /**
     * Test case to verify that prepareJoinOrHostView() correctly interacts with the associated View.
     * It checks if the prepareGUI() method is called exactly once on the mockView.
     */
    @Test
    void testPrepareJoinOrHostView() {
        leaveRoomPresenter.prepareJoinOrHostView();
        Mockito.verify(mockView, Mockito.times(1)).prepareGUI(null); // check that prepareGUI was called
    }
    /**
     * Test case to verify that prepareFailedToLeaveRoomView() correctly interacts with the associated View.
     * It checks if the displayPopUpMessage() method is called exactly once on the mockView.
     */
    @Test
    void testPrepareFailedToLeaveRoomView() {
        leaveRoomPresenter.prepareFailedToLeaveRoomView();
        Mockito.verify(mockView, Mockito.times(1)).displayPopUpMessage("Failed to leave the room.");
    }
}
