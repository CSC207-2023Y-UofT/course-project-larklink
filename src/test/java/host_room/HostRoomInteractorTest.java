package host_room;

import database.RoomDBGateway;
import leave_room.LeaveRoomInteractor;
import leave_room.LeaveRoomOutputBoundary;
import models.RoomDBModel;
import models.RoomModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;

public class HostRoomInteractorTest {
    private HostRoomInteractor hostRoomInteractor;
    @Mock
    private RoomDBGateway roomDBGatewayMock;
    @Mock
    private HostRoomOutputBoundary hostRoomOuputBoundaryMock;

    private int roomID;
    private int userID;
    private int userID2;

    @BeforeEach
    public void setup() {
        roomID = 11;
        userID = 11;
        userID2 = 121;

        roomDBGatewayMock = Mockito.mock(RoomDBGateway.class);
        hostRoomOuputBoundaryMock = Mockito.mock(HostRoomOutputBoundary.class);
        hostRoomInteractor = new HostRoomInteractor(roomDBGatewayMock, hostRoomOuputBoundaryMock);
    }

    @Test
    public void testHostRoomSuccess() {
        RoomDBModel room = new RoomDBModel(roomID, new ArrayList<>(Arrays.asList(userID, userID2)),
                userID2, "Test Room", "");

    }
}
