package use_cases_and_adapters.host_room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class HostRoomControllerTest {
    private HostRoomController hostRoomController;
    private HostRoomInputBoundary hostRoomInputBoundaryMock;
    private String testRoomName;

    @BeforeEach
    public void setup() {
        hostRoomInputBoundaryMock = Mockito.mock(HostRoomInputBoundary.class);
        hostRoomController = new HostRoomController(hostRoomInputBoundaryMock);
        testRoomName = "testName";
    }

    @Test
    public void testHandleHostRoom() {
        hostRoomController.handleHostRoom(testRoomName);
        verify(hostRoomInputBoundaryMock, times(1)).hostRoom(testRoomName);
    }
}
