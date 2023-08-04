package use_cases_and_adapters.leave_room;

import entities.Room;
import entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_cases_and_adapters.RoomDBModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
/**
 * The LeaveRoomInteractorTest class contains unit tests for the LeaveRoomInteractor class.
 * The interfaces LeaveRoomOutputBoundary and LeaveRoomDBGateway are mocked using Mockito
 * and the leaveRoom() method is tested in various circumstances.
 */
public class LeaveRoomInteractorTest {
    private LeaveRoomInteractor leaveRoomInteractor;
    private LeaveRoomDBGateway roomDBGatewayMock;
    private LeaveRoomOutputBoundary leaveRoomOutputBoundaryMock;
    private int roomID;
    private int userID;
    private int userID2;
    /**
     * Setup method to initialize the test environment.
     * It creates mocks of LeaveRoomDBGateway and LeaveRoomOutputBoundary using Mockito
     * and initializes the LeaveRoomInteractor with these mock objects.
     */
    @BeforeEach
    public void setup() {
        roomID = 1;
        userID = 111;
        userID2 = 222;

        roomDBGatewayMock = Mockito.mock(LeaveRoomDBGateway.class);
        leaveRoomOutputBoundaryMock = Mockito.mock(LeaveRoomOutputBoundary.class);
        leaveRoomInteractor = new LeaveRoomInteractor(roomDBGatewayMock, leaveRoomOutputBoundaryMock);
    }
    /**
     * Test case to verify that leaveRoom() successfully removes the user from the room's active user list
     * and calls the leaveARoom() method on the LeaveRoomDBGateway mock.
     * It also checks that prepareJoinOrHostView() is called on the LeaveRoomOutputBoundary mock.
     */
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
    /**
     * Test case to verify that leaveRoom() does not remove the user from the room's active user list
     * and does not call the leaveARoom() method on the LeaveRoomDBGateway mock.
     * It checks that prepareFailedToLeaveRoomView() is called on the LeaveRoomOutputBoundary mock.
     */
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
