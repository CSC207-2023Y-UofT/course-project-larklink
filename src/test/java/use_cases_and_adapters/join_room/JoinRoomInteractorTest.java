package use_cases_and_adapters.join_room;

import use_cases_and_adapters.RoomDBModel;
import entities.User;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.util.*;

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
                "111", "[15:38:42] alice: hi\n"),
                new RoomDBModel(50, "existingRoom", 555,
                        "555", "[15:38:42] nadine: sup\n"));
    }

    /**
     * Tests handleJoinRoom with a non-existing room name.
     */
    @Test public void testHandleJoinRoomFailure(){
        User.setUser(visitorID, visitorName, visitorPW);

        Mockito.when(database.getRooms()).thenReturn(testRooms);
        interactor.handleJoinRoom("nonExistingRoomName");

        // checks that presenter calls prepareFailView exactly once
        Mockito.verify(presenter, Mockito.times(1)).prepareFailView();
        // checks that presenter never calls prepareRoomView
        Mockito.verify(presenter, Mockito.never()).prepareRoomView(Mockito.any(String.class));

        // checks that database never calls updateARoom so room database is not updated
        Mockito.verify(database, Mockito.never()).updateARoom(Mockito.any(RoomDBModel.class));
    }

    /**
     * Tests handleJoinRoom with an existing room name.
     */
    @Test public void testHandleJoinRoomSuccess(){
        User.setUser(visitorID, visitorName, visitorPW);
        // checks that this user's ID is not in the active user list of room 'existingRoom' before the user enters
        assert !testRooms.get(1).getActiveUserIDs().contains(User.getUserID());

        Mockito.when(database.getRooms()).thenReturn(testRooms);
        interactor.handleJoinRoom("existingRoom");

        ArgumentCaptor<RoomDBModel> captor = ArgumentCaptor.forClass(RoomDBModel.class);

        // checks that database calls updateARoom method exactly once with the correct room
        Mockito.verify(database, Mockito.times(1)).updateARoom(captor.capture());

        RoomDBModel enteredRoom = captor.getValue(); // get the argument that was passed to updateARoom

        // checks that presenter calls prepareRoomView exactly once with the correct message history
        Mockito.verify(presenter, Mockito.times(1)).prepareRoomView(enteredRoom.getMessageHistory());
        assert enteredRoom.getMessageHistory().equals("[15:38:42] nadine: sup\n");

        // checks that presenter never calls prepareFailView
        Mockito.verify(presenter, Mockito.never()).prepareFailView();

        // checks that this user's ID is saved in active user list of the room 'existingRoom'
        assert enteredRoom.getActiveUserIDs().contains(User.getUserID());
    }

    /**
     * Tests loadRoomNames.
     */
    @Test public void testLoadRoomNames() {
        Mockito.when(database.getRooms()).thenReturn(testRooms);
        List<String> expectedRoomNames = new ArrayList<>(List.of("FakeRoom", "existingRoom"));

        // checks this method returns a list of room names which is the same as room names from database
        assert interactor.loadRoomNames().equals(expectedRoomNames);
    }
}