package leave_room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import database.*;
import models.*;
import java.util.ArrayList;
import java.util.Arrays;
import static org.mockito.Mockito.*;

public class LeaveRoomInteractorTest {
    private LeaveRoomInteractor leaveRoomInteractor;
    private RoomDBGateway roomDBGatewayMock;
    private LeaveRoomOutputBoundary leaveRoomOutputBoundaryMock;
    private int roomID;
    private int userID;
    private int userID2;

    @BeforeEach
    public void setup() {
        roomID = 1;
        userID = 111;
        userID2 = 222;

        roomDBGatewayMock = Mockito.mock(RoomDBGateway.class);
        leaveRoomOutputBoundaryMock = Mockito.mock(LeaveRoomOutputBoundary.class);
        leaveRoomInteractor = new LeaveRoomInteractor(roomDBGatewayMock, leaveRoomOutputBoundaryMock);
    }

    @Test
    public void testLeaveRoomSuccessfully() {
        RoomDBModel room = new RoomDBModel(roomID, new ArrayList<>(Arrays.asList(userID, userID2)), userID2, "Test Room");
        when(roomDBGatewayMock.getARoom(roomID)).thenReturn(room);
        leaveRoomInteractor.leaveRoom(roomID, userID);
        verify(roomDBGatewayMock, times(1)).leaveARoom(room);
        verify(leaveRoomOutputBoundaryMock, times(1)).prepareJoinOrHostView(userID);
    }

    @Test
    public void testLeaveRoomFailed() {
        RoomDBModel room = new RoomDBModel(roomID, new ArrayList<>(Arrays.asList(userID2)), userID2, "Test Room");
        when(roomDBGatewayMock.getARoom(roomID)).thenReturn(room);
        leaveRoomInteractor.leaveRoom(roomID, userID);
        verify(roomDBGatewayMock, never()).leaveARoom(room);
        verify(leaveRoomOutputBoundaryMock, times(1)).prepareFailedToLeaveRoomView();
    }
}
