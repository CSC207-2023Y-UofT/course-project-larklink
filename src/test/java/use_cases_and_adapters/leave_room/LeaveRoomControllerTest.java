package use_cases_and_adapters.leave_room;

import org.junit.jupiter.api.*;
import org.mockito.*;

/**
 * The LeaveRoomControllerTest class contains unit tests for the LeaveRoomController class.
 * It tests the handleLeaveRoom() method by creating a mock LeaveRoomInputBoundary using Mockito.
 */
public class LeaveRoomControllerTest {
    private LeaveRoomController leaveRoomController;
    private LeaveRoomInputBoundary leaveRoomInputBoundaryMock;
    /**
     * Setup method to initialize the test environment.
     * It builds a mock LeaveRoomInputBoundary and uses it to initialize the LeaveRoomController using Mockito.
     */
    @BeforeEach
    public void setup() {
        leaveRoomInputBoundaryMock = Mockito.mock(LeaveRoomInputBoundary.class);
        leaveRoomController = new LeaveRoomController(leaveRoomInputBoundaryMock);
    }
    /**
     * Test case to verify if the handleLeaveRoom() method correctly calls leaveRoom() on the LeaveRoomInputBoundary.
     * It checks if the leaveRoom() method is invoked exactly once on the mocked LeaveRoomInputBoundary.
     */
    @Test
    public void testHandleLeaveRoom() {
        leaveRoomController.handleLeaveRoom();
        Mockito.verify(leaveRoomInputBoundaryMock, Mockito.times(1)).leaveRoom();
    }
}

