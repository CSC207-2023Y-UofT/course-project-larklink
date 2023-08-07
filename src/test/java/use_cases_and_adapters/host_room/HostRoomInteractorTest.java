package use_cases_and_adapters.host_room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases_and_adapters.RoomDBModel;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    @Test
    void testHostRoom_NewRoom() {
        String roomName = "TestRoom";
        List<RoomDBModel> existingRooms = new ArrayList<>();
        when(database.getRooms()).thenReturn(existingRooms);

        interactor.hostRoom(roomName);

        verify(database, times(1)).updateARoom(any(RoomDBModel.class));
        verify(presenter, times(1)).prepareRoomView(anyString());
    }

    @Test
    void testHostRoom_DuplicateRoomName() {
        String roomName = "ExistingRoom";
        List<RoomDBModel> existingRooms = new ArrayList<>();
        existingRooms.add(new RoomDBModel(1, roomName, 1, "", ""));
        when(database.getRooms()).thenReturn(existingRooms);

        interactor.hostRoom(roomName);

        verify(database, never()).updateARoom(any(RoomDBModel.class));
        verify(presenter, times(1)).prepareDuplicateNameView();
    }

}

