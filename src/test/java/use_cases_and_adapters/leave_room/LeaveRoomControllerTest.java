package use_cases_and_adapters.leave_room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class LeaveRoomControllerTest {
    private LeaveRoomController leaveRoomController;
    private LeaveRoomInputBoundary leaveRoomInputBoundaryMock;

    @BeforeEach
    public void setup() {
        leaveRoomInputBoundaryMock = Mockito.mock(LeaveRoomInputBoundary.class);
        leaveRoomController = new LeaveRoomController(leaveRoomInputBoundaryMock);
    }

    @Test
    public void testHandleLeaveRoom() {
        leaveRoomController.handleLeaveRoom();
        verify(leaveRoomInputBoundaryMock, times(1)).leaveRoom();
    }
}

