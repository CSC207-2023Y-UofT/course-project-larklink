package join_room;


import database.RoomDBModel;
import entities.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.*;

public class JoinRoomInteractorTest {
    private JoinRoomInteractor interactor;
    @Mock
    private JoinRoomDBGateway mockDB;
    @Mock
    private JoinRoomOutputBoundary mockPresenter;
    private Integer visitorID;
    private String visitorName;
    private String visitorPW;
    private List<RoomDBModel> rooms;

    @BeforeEach
    public void setUp() {
        mockDB = mock(JoinRoomDBGateway.class);
        mockPresenter = mock(JoinRoomOutputBoundary.class);
        interactor = new JoinRoomInteractor(mockDB, mockPresenter);

        visitorID = 777;
        visitorName = "testUser";
        visitorPW = "12345678";
        rooms = List.of(new RoomDBModel(5, "existingRoomName", 555,
                new ArrayList<>(List.of(555)), ""));
    }

    @Test public void testHandleJoinRoom_nonExistingRoom(){
        User.setUser(visitorID, visitorName, visitorPW);

        when(mockDB.getRooms()).thenReturn(rooms);
        interactor.handleJoinRoom("nonExistingRoomName");

        verify(mockPresenter).prepareFailView();
        verify(mockDB, never()).joinARoom(any(Integer.class), any(RoomDBModel.class));
    }
    @Test public void testHandleJoinRoom_existingRoom(){
        User.setUser(visitorID, visitorName, visitorPW);

        RoomDBModel roomDBmodel = rooms.get(0);
        when(mockDB.getRooms()).thenReturn(rooms);
        interactor.handleJoinRoom("existingRoomName");

        verify(mockPresenter).prepareRoomView(roomDBmodel.getMessageHistory());
        verify(mockDB, times(1)).joinARoom(User.getUserID(), roomDBmodel);
        assert Objects.equals(roomDBmodel.getActiveUserIDs().get(1), visitorID);
    }
}