package leave_room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.leave_room.leave_room.LeaveRoomController;
import use_case.leave_room.leave_room.LeaveRoomInputBoundary;

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

