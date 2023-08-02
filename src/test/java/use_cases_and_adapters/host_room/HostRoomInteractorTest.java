package use_cases_and_adapters.host_room;

import entities.Room;
import entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_cases_and_adapters.RoomDBModel;
import use_cases_and_adapters.UserDBModel;
import use_cases_and_adapters.signup_and_login.UserModel;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class HostRoomInteractorTest {
    private HostRoomInteractor interactor;
    @Mock
    private HostRoomDBGateway mockDB;
    @Mock
    private HostRoomOutputBoundary mockPresenter;
    private Integer testRoomID;
    private String testName;

    private Integer testHostID;
    private String testMessageHistory;
    private List<RoomDBModel> testRooms;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        interactor = new HostRoomInteractor(mockDB, mockPresenter);

        // initialize shared variables
        testName = "testRoom";
        testHostID = 11;
        testRoomID = 11;
        testMessageHistory = "";
        testRooms = List.of(new RoomDBModel(testRoomID, testName, testHostID, new ArrayList<>(),
                testMessageHistory));

    }

    @Test
    public void testHostRoom_success() {
        when(mockDB.getRooms()).thenReturn(testRooms);

        RoomDBModel newRoom = new RoomDBModel(testRoomID, testName, testHostID, new ArrayList<>(),
                testMessageHistory);
        interactor.hostRoom(testName);

        ArgumentCaptor<RoomDBModel> captor = ArgumentCaptor.forClass(RoomDBModel.class);
        verify(mockDB).updateARoom(captor.capture()); // check that we did add a user to the database
        RoomDBModel addedRoom = captor.getValue(); // get the argument that was passed to addAUser

        assert addedRoom.getRoomID() == 3; // since testUserID == 2
        assert addedRoom.getRoomName().equals(newRoom.getRoomName()); // works
        verify(mockPresenter).prepareRoomView("");
    }

}
