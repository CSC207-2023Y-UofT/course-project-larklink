import leave_room.*;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class LeaveRoomControllerTest {
    @Test
    public void testHandleLeaveRoom() {
        String roomId = "room1";
        String currUserId = "user1";
        // Mock the interface
        LeaveRoomInputBoundary mockInputBoundary = mock(LeaveRoomInputBoundary.class);
        LeaveRoomController controller = new LeaveRoomController(mockInputBoundary);
        controller.handleLeaveRoom(roomId, currUserId);
        verify(mockInputBoundary, times(1)).leaveRoom(roomId, currUserId);
    }
}
