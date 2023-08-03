package use_cases_and_adapters.join_room;



import entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import use_cases_and_adapters.RoomDBModel;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

/**
 * This class tests JoinRoomInteractor.
 */
public class JoinRoomInteractorTest {
    private JoinRoomInteractor interactor;
    @Mock
    private JoinRoomDBGateway database;
    @Mock
    private JoinRoomOutputBoundary presenter;
    private Integer visitorID;
    private String visitorName;
    private String visitorPW;
    private List<RoomDBModel> testRooms;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        interactor = new JoinRoomInteractor(database, presenter);
        visitorID = 777;
        visitorName = "testUser";
        visitorPW = "12345678";
        testRooms = List.of(new RoomDBModel(10, "FakeRoom", 111,
                new ArrayList<>(List.of(111)), "[15:38:42] alice: hi\n"),
                new RoomDBModel(50, "existingRoom", 555,
                        new ArrayList<>(List.of(555)), "[15:38:42] nadine: sup\n"));
    }

    /**
     * Tests handleJoinRoom with a non-existing room name.
     */
    @Test public void testHandleJoinRoomFailure(){
        User.setUser(visitorID, visitorName, visitorPW);

        when(database.getRooms()).thenReturn(testRooms);
        interactor.handleJoinRoom("nonExistingRoomName");

        // checks that presenter calls prepareFailView exactly once
        verify(presenter, times(1)).prepareFailView();
        // checks that presenter never calls prepareRoomView
        verify(presenter, never()).prepareRoomView(any(String.class));

        // checks that database never calls updateARoom so room database is not updated
        verify(database, never()).updateARoom(any(RoomDBModel.class));
    }

    /**
     * Tests handleJoinRoom with an existing room name.
     */
    @Test public void testHandleJoinRoomSuccess(){
        User.setUser(visitorID, visitorName, visitorPW);
        // checks that this user's ID is not in the active user list of room 'existingRoom' before the user enters
        assertFalse(testRooms.get(1).getActiveUserIDs().contains(User.getUserID()));

        when(database.getRooms()).thenReturn(testRooms);
        interactor.handleJoinRoom("existingRoom");

        ArgumentCaptor<RoomDBModel> captor = ArgumentCaptor.forClass(RoomDBModel.class);

        // checks that database calls updateARoom method exactly once with the correct room
        verify(database, times(1)).updateARoom(captor.capture());

        RoomDBModel enteredRoom = captor.getValue(); // get the argument that was passed to updateARoom

        // checks that presenter calls prepareRoomView exactly once with the correct message history
        verify(presenter, times(1)).prepareRoomView(enteredRoom.getMessageHistory());
        assertEquals(enteredRoom.getMessageHistory(), "[15:38:42] nadine: sup\n");

        // checks that presenter never calls prepareFailView
        verify(presenter, never()).prepareFailView();

        // checks that this user's ID is saved in active user list of the room 'existingRoom'
        assertTrue(enteredRoom.getActiveUserIDs().contains(User.getUserID()));
    }

    /**
     * Tests loadRoomNames.
     */
    @Test public void testLoadRoomNames() {
        when(database.getRooms()).thenReturn(testRooms);
        List<String> expectedRoomNames = new ArrayList<>(List.of("FakeRoom", "existingRoom"));

        // checks this method returns a list of room names which is the same as room names from database
        assertEquals(expectedRoomNames, interactor.loadRoomNames());
    }
}