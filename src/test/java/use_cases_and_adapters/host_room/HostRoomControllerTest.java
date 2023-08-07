package use_cases_and_adapters.host_room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
<<<<<<< HEAD
=======

>>>>>>> 862fca0e3dcb0c4b7f8a4976afbece0bdd8cab25
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test the host room controller (as the name suggests)
 */

public class HostRoomControllerTest {
    private HostRoomController hostRoomController;
    private HostRoomInputBoundary hostRoomInputBoundaryMock;
    private String testRoomName;

    @BeforeEach
    public void setup() {
        // mock the input boundary
        hostRoomInputBoundaryMock = Mockito.mock(HostRoomInputBoundary.class);
        hostRoomController = new HostRoomController(hostRoomInputBoundaryMock);
        testRoomName = "testName";
    }

    /**
     * Test that host room method called as expected
     */
    @Test
    public void testHandleHostRoom() {
<<<<<<< HEAD
        // host a room
=======
>>>>>>> 862fca0e3dcb0c4b7f8a4976afbece0bdd8cab25
        hostRoomController.handleHostRoom(testRoomName);
        // verify the method is called
        verify(hostRoomInputBoundaryMock, times(1)).hostRoom(testRoomName);
    }
}
