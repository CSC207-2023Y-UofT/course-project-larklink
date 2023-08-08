package use_cases_and_adapters.host_room;
import entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases_and_adapters.RoomDBModel;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Test host room interactor (as the name suggests)
 */


class HostRoomInteractorTest {
    private HostRoomDBGateway database;
    private HostRoomOutputBoundary presenter;
    private HostRoomInteractor interactor;

    @BeforeEach
    void setUp() {
        database = mock(HostRoomDBGateway.class);
        presenter = mock(HostRoomOutputBoundary.class);
        interactor = new HostRoomInteractor(database, presenter);
    }

    /**
     * Ensure that we can add rooms to DB and that the room view is shown
     * when this is done
     */
    @Test
    void testHostRoom_NewRoom() {
        String roomName = "TestRoom";
        List<RoomDBModel> existingRooms = new ArrayList<>();
        when(database.getRooms()).thenReturn(existingRooms);

        interactor.hostRoom(roomName);

        // room added to db
        verify(database, times(1)).updateARoom(any(RoomDBModel.class));
        // correct view displayed
        verify(presenter, times(1)).prepareRoomView(anyString());
    }

    /**
     * Verify duplicate room name displayed when we try to create two rooms with
     * the same name
     */

    @Test
    void testHostRoom_DuplicateRoomName() {
        String roomName = "CoolName";
        List<RoomDBModel> existingRooms = new ArrayList<>();
        // create a new room
        existingRooms.add(new RoomDBModel(1, roomName, 1, "", ""));
        // return a list with the existing room when db is queried
        when(database.getRooms()).thenReturn(existingRooms);

        interactor.hostRoom(roomName);
        // we should not add the room again because there would be duplicate names
        verify(database, never()).updateARoom(any(RoomDBModel.class));
        // ensure that the duplicate name view is displayed
        verify(presenter, times(1)).prepareDuplicateNameView();
    }


    /**
     * Ensure that there cannot be two rooms hosted by the same user
     */
    @Test
    void testHostRoom_MultipleHostingView() {
        String roomName = "NewRoom";
        List<RoomDBModel> existingRooms = new ArrayList<>();
        // put room with hostid 1 in db (this will collide with new attempt)
        existingRooms.add(new RoomDBModel(1, "room1", 1, "", ""));
        when(database.getRooms()).thenReturn(existingRooms);

        // don't switch views when called
        doNothing().when(presenter).prepareMultipleHostingView(anyString());
        User.setUser(1, "username", "password");
        interactor.hostRoom(roomName);

        // we shouldn't have added a new room
        verify(database, never()).updateARoom(any(RoomDBModel.class));
        // called prepareMultipleHostingView as required
        verify(presenter, times(1)).prepareMultipleHostingView("room1");
    }

}

