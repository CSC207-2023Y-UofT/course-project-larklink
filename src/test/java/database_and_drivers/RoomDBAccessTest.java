package database_and_drivers;

import database_and_drivers.converters.RoomConverter;
import use_cases_and_adapters.RoomDBModel;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.util.*;

public class RoomDBAccessTest {

    @Mock
    private RoomDBModel mockRoomDBModel;


    private RoomDBAccess roomDBAccess;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // initialize RoomDBAccess and a spy to track it
        roomDBAccess = Mockito.spy(new RoomDBAccess(new RoomConverter()));
    }

    @Test
    public void testGetRooms() {
        // here we are just checking that a call to getRooms is a call to getRows
        List<RoomDBModel> expectedRooms = Collections.singletonList(mockRoomDBModel);
        Mockito.doReturn(expectedRooms).when(roomDBAccess).getRows();
        List<RoomDBModel> actualRooms = roomDBAccess.getRooms();
        assert expectedRooms == actualRooms;
    }

    @Test
    public void testGetARoom() {
        // here we are just checking that a call to getARoom is a call to getRow
        int roomId = 123; // Example room ID
        Mockito.doReturn(mockRoomDBModel).when(roomDBAccess).getARow(roomId);
        RoomDBModel actualRoom = roomDBAccess.getARoom(roomId);
        assert mockRoomDBModel == actualRoom;
    }

    @Test
    public void testUpdateARoom() {
        // here we are just checking that a call to addARoom is a call to updateARow
        Mockito.doNothing().when(roomDBAccess).updateARow(Mockito.anyInt(), Mockito.any());
        roomDBAccess.updateARoom(mockRoomDBModel);
        Mockito.verify(roomDBAccess, Mockito.times(1)).updateARow(Mockito.anyInt(), Mockito.any());
    }

    @Test
    public void testGetRoute() {
        // test if the getRoute method returns the expected string
        assert roomDBAccess.getRoute().equals("rooms");
    }
}
