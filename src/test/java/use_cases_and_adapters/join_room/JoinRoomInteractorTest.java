package use_cases_and_adapters.join_room;


import entities.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import use_cases_and_adapters.RoomDBModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Test class for JoinRoomInteractor.
 */
public class JoinRoomInteractorTest {
    private JoinRoomInteractor interactor;
    @Mock
    private JoinRoomDBGateway mockDB;
    @Mock
    private JoinRoomOutputBoundary mockPresenter;
    private Integer visitorID;
    private String visitorName;
    private String visitorPW;
    private List<RoomDBModel> testRooms;

    @BeforeEach
    public void setUp() {
        mockDB = mock(JoinRoomDBGateway.class);
        mockPresenter = mock(JoinRoomOutputBoundary.class);
        interactor = new JoinRoomInteractor(mockDB, mockPresenter);

        visitorID = 777;
        visitorName = "testUser";
        visitorPW = "12345678";
        testRooms = List.of(new RoomDBModel(10, "FakeRoom", 111,
                new ArrayList<>(List.of(555)), "[15:38:42] alice: hi\n"),
                new RoomDBModel(50, "existingRoomName", 555,
                        new ArrayList<>(List.of(555)), "[15:38:42] nadine: sup\n"));
    }

    /**
     * Tests 'handleJoinRoom' with non-existing room name.
     * verifies that mockPresenter calls 'prepareFailView' exactly once.
     * verifies that mockDB does not call 'joinARoom'
     */
    @Test public void testHandleJoinRoom_nonExistingRoom(){
        User.setUser(visitorID, visitorName, visitorPW);

        when(mockDB.getRooms()).thenReturn(testRooms);
        interactor.handleJoinRoom("nonExistingRoomName");

        verify(mockPresenter, times(1)).prepareFailView();
        verify(mockDB, never()).updateARoom(any(RoomDBModel.class));
    }

    /**
     * Tests 'handleJoinRoom' with existing room name.
     * Verifies that mockPresenter calls 'prepareRoomView' exactly once with message history form correct room.
     * Verifies that mockDB calls 'joinARoom' exactly once with correct user ID and room.
     * Asserts that this user's ID is saved in active user list of the room.
     */
    @Test public void testHandleJoinRoom_existingRoom(){
        User.setUser(visitorID, visitorName, visitorPW);

        when(mockDB.getRooms()).thenReturn(testRooms);
        interactor.handleJoinRoom("existingRoomName");

        verify(mockPresenter, times(1)).prepareRoomView(testRooms.get(1).getMessageHistory());
        verify(mockDB, times(1)).updateARoom(testRooms.get(1));
        assert Objects.equals(testRooms.get(1).getActiveUserIDs().get(1), visitorID);
    }

    /**
     * Tests 'loadRoomNames'.
     * Asserts this method returns a list of room names which is the same as room names from mockDB.
     */
    @Test public void testLoadRoomNames() {
        when(mockDB.getRooms()).thenReturn(testRooms);
        List<String> expectedRoomNames = new ArrayList<>(List.of("FakeRoom", "existingRoomName"));
        assertEquals(expectedRoomNames, interactor.loadRoomNames());
    }
}