package join_room;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;

import static org.mockito.Mockito.*;

/**
 * Test class for JoinRoomController.
 */
public class JoinRoomControllerTest {
    @Mock
    private JoinRoomInputBoundary mockInputBoundary;
    private JoinRoomController controller;
    private String testRoomName;

    @BeforeEach
    public void setUp() {
        mockInputBoundary = mock(JoinRoomInputBoundary.class);
        controller = new JoinRoomController(mockInputBoundary);
        testRoomName = "MyRoom";
    }

    /**
     * Tests a method 'handleJoinRoomByRoomName'.
     * Verifies that mockInputBoundary calls 'handleJoinRoom' exactly once with given testRoomName.
     */
    @Test
    public void testHandleJoinRoomByRoomName() {
        controller.handleJoinRoomByRoomName(testRoomName);
        verify(mockInputBoundary, times(1)).handleJoinRoom(testRoomName);
    }

    /**
     * Tests a method 'loadRoomNames'.
     * Verifies if mockInputBoundary calls 'loadRoomNames' exactly once.
     */
    @Test
    public void loadRoomNames() {
        controller.loadRoomNames();
        verify(mockInputBoundary, times(1)).loadRoomNames();
    }




}