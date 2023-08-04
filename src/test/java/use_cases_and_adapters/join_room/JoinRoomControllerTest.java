package use_cases_and_adapters.join_room;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

/**
 * This class tests JoinRoomController.
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
     * Tests handleJoinRoomByRoomName.
     */
    @Test
    public void testHandleJoinRoomByRoomName() {
        controller.handleJoinRoomByRoomName(testRoomName);

        // checks that inputBoundary calls handleJoinRoom exactly once with the given room name
        verify(inputBoundary, times(1)).handleJoinRoom(testRoomName);
    }

    /**
     * Tests loadRoomNames.
     */
    @Test
    public void loadRoomNames() {
        controller.loadRoomNames();

        // checks that inputBoundary calls loadRoomNames exactly once
        verify(inputBoundary, times(1)).loadRoomNames();
    }
}