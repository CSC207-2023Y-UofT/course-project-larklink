import leave_room.*;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class LeaveRoomControllerTest {
    @Test
    public void testHandleLeaveRoom() {
        Integer roomId = 123;
        Integer currUserId = 111;
        // Mock the interface
        LeaveRoomInputBoundary mockInputBoundary = mock(LeaveRoomInputBoundary.class);
        LeaveRoomController controller = new LeaveRoomController(mockInputBoundary);
        controller.handleLeaveRoom(123, 111);
        verify(mockInputBoundary, times(1)).leaveRoom(roomId, currUserId);
    }
}
