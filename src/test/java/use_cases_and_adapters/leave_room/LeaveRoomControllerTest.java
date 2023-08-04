package use_cases_and_adapters.leave_room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_cases_and_adapters.leave_room.LeaveRoomController;


import static org.mockito.Mockito.*;
/**
 * The LeaveRoomControllerTest class contains unit tests for the LeaveRoomController class.
 * It tests the handleLeaveRoom() method by creating a mock of LeaveRoomInputBoundary using Mockito.
 */
public class LeaveRoomControllerTest {
    private LeaveRoomController leaveRoomController;
    private LeaveRoomInputBoundary leaveRoomInputBoundaryMock;
    /**
     * Setup method to initialize the test environment.
     * It builds a mock of the LeaveRoomInputBoundary and uses it to initialize the LeaveRoomController using Mockito.
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
        verify(leaveRoomInputBoundaryMock, times(1)).leaveRoom();
    }
}

