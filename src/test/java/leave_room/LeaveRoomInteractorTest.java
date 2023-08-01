package leave_room;

import entities.Room;
import entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_cases.RoomDBModel;
import use_cases.leave_room.LeaveRoomDBGateway;
import use_cases.leave_room.LeaveRoomInteractor;
import use_cases.leave_room.LeaveRoomOutputBoundary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class LeaveRoomInteractorTest {
    private LeaveRoomInteractor leaveRoomInteractor;
    private LeaveRoomDBGateway roomDBGatewayMock;
    private LeaveRoomOutputBoundary leaveRoomOutputBoundaryMock;
    private int roomID;
    private int userID;
    private int userID2;

    @BeforeEach
    public void setup() {
        roomID = 1;
        userID = 111;
        userID2 = 222;

        roomDBGatewayMock = Mockito.mock(LeaveRoomDBGateway.class);
        leaveRoomOutputBoundaryMock = Mockito.mock(LeaveRoomOutputBoundary.class);
        leaveRoomInteractor = new LeaveRoomInteractor(roomDBGatewayMock, leaveRoomOutputBoundaryMock);
    }

    @Test
    public void testLeaveRoomSuccessfully() {
        Room.setRoom(roomID, "Test Room", userID2, new ArrayList<>(Arrays.asList(userID, userID2)), ""); // simulate getting a room
        User.setUser(userID, "", ""); // simulate logging in a user

        RoomDBModel room = new RoomDBModel(roomID, "Test Room", userID2, new ArrayList<>(Arrays.asList(userID, userID2)),"");
        when(roomDBGatewayMock.getARoom(roomID)).thenReturn(room);
        leaveRoomInteractor.leaveRoom();
        verify(roomDBGatewayMock, times(1)).updateARoom(room);
        verify(leaveRoomOutputBoundaryMock, times(1)).prepareJoinOrHostView();
    }

    @Test
    public void testLeaveRoomFailed() {
        Room.setRoom(roomID, "Test Room", userID2, new ArrayList<>(List.of(userID2)), ""); // simulate getting a room
        User.setUser(userID, "", ""); // simulate logging in a user

        RoomDBModel room = new RoomDBModel(roomID, "Test Room", userID2, new ArrayList<>(List.of(userID2)),"");
        when(roomDBGatewayMock.getARoom(roomID)).thenReturn(room);
        leaveRoomInteractor.leaveRoom();
        verify(roomDBGatewayMock, never()).updateARoom(room);
        verify(leaveRoomOutputBoundaryMock, times(1)).prepareFailedToLeaveRoomView();
    }
}
