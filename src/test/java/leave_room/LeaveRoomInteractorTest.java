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

    @BeforeEach
    public void setup() {
        roomDBGatewayMock = Mockito.mock(RoomDBGateway.class);
        leaveRoomOutputBoundaryMock = Mockito.mock(LeaveRoomOutputBoundary.class);
        leaveRoomInteractor = new LeaveRoomInteractor(roomDBGatewayMock, leaveRoomOutputBoundaryMock);
    }

    @Test
    public void testLeaveRoomSuccessfully() {
        int roomID = 1;
        int userID = 111;
        int userID2 = 222;

        RoomDBModel room = new RoomDBModel(roomID, new ArrayList<>(Arrays.asList(userID, userID2)), userID2, "Test Room");
        when(roomDBGatewayMock.getARoom(roomID)).thenReturn(room);
        leaveRoomInteractor.leaveRoom(roomID, userID);
        verify(roomDBGatewayMock, times(1)).leaveARoom(room);
        verify(leaveRoomOutputBoundaryMock, times(1)).prepareJoinOrHostView();
    }

    @Test
    public void testLeaveRoomFailed() {
        int roomID = 1;
        int userID = 111;
        int userID2 = 222;

        RoomDBModel room = new RoomDBModel(roomID, new ArrayList<>(Arrays.asList(userID2 )), userID2 , "Test Room");
        when(roomDBGatewayMock.getARoom(roomID)).thenReturn(room);
        leaveRoomInteractor.leaveRoom(roomID, userID);
        verify(roomDBGatewayMock, never()).leaveARoom(room);
        verify(leaveRoomOutputBoundaryMock, times(1)).prepareFailedToLeaveRoomView();
    }
}

