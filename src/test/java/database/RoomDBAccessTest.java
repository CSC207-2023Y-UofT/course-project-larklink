package database;

import database.converters.RoomConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class RoomDBAccessTest {

    @Mock
    private RoomDBModel mockRoomDBModel;

    @Mock
    private HttpClient mockHttpClient;

    private RoomDBAccess roomDBAccess;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        roomDBAccess = Mockito.spy(new RoomDBAccess(mockHttpClient, new RoomConverter())); // initialize RoomDBAccess and a spy to track it
    }

    @Test
    public void testGetRooms() throws IOException {
        // here we are just checking that a call to getRooms is a call to getRows
        List<RoomDBModel> expectedRooms = Collections.singletonList(mockRoomDBModel);
        doReturn(expectedRooms).when(roomDBAccess).getRows();
        List<RoomDBModel> actualRooms = roomDBAccess.getRooms();
        assertEquals(expectedRooms, actualRooms);
    }

    @Test
    public void testGetARoom() throws IOException {
        // here we are just checking that a call to getARoom is a call to getRow
        int roomId = 123; // Example room ID
        doReturn(mockRoomDBModel).when(roomDBAccess).getARow(roomId);
        RoomDBModel actualRoom = roomDBAccess.getARoom(roomId);
        assertEquals(mockRoomDBModel, actualRoom);
    }

    @Test
    public void testUpdateARoom() throws IOException {
        // here we are just checking that a call to addARoom is a call to updateARow
        doNothing().when(roomDBAccess).updateARow(anyInt(), any());
        roomDBAccess.updateARoom(mockRoomDBModel);
        verify(roomDBAccess, times(1)).updateARow(anyInt(), any());
    }

    @Test
    public void testGetRoute() {
        // test if the getRoute method returns the expected string
        assertEquals("rooms", roomDBAccess.getRoute());
    }
}
