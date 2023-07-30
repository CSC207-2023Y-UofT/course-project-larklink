package join_room;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;

import static org.mockito.Mockito.*;


public class JoinRoomControllerTest {
    private JoinRoomController controller;
    @Mock
    private JoinRoomInputBoundary mockInputBoundary;
    private String roomName;

    @BeforeEach
    public void setUp() {
        mockInputBoundary = mock(JoinRoomInputBoundary.class);
        controller = new JoinRoomController(mockInputBoundary);
        roomName = "testRoom";
    }

    @Test
    public void testFormatAndHandleJoinRoom() {
        controller.formatAndHandleJoinRoom(roomName);
        verify(mockInputBoundary, times(1)).handleJoinRoom(roomName);
    }


}