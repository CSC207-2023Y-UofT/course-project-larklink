package use_cases_and_adapters.join_room;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

/**
 * Test class for JoinRoomController.
 */
public class JoinRoomControllerTest {
    @Mock
    private JoinRoomInputBoundary inputBoundary;
    private JoinRoomController controller;
    private String testRoomName;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        controller = new JoinRoomController(inputBoundary);
        testRoomName = "MyRoom";
    }

    /**
     * Tests handleJoinRoomByRoomName method.
     */
    @Test
    public void testHandleJoinRoomByRoomName() {
        controller.handleJoinRoomByRoomName(testRoomName);

        // checks that mockInputBoundary calls handleJoinRoom method exactly once with given testRoomName
        verify(inputBoundary, times(1)).handleJoinRoom(testRoomName);
    }

    /**
     * Tests loadRoomNames method.
     */
    @Test
    public void loadRoomNames() {
        controller.loadRoomNames();

        // checks that inputBoundary calls loadRoomNames method exactly once
        verify(inputBoundary, times(1)).loadRoomNames();
    }
}