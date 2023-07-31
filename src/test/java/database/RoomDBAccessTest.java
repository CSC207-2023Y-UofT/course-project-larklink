package database;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RoomDBAccessTest {

    private RoomDBAccess roomDBAccess;

    @Mock
    private RoomDBModel mockRoomDBModel;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        String urlBase = "http://localhost:8080/api/";
        roomDBAccess = Mockito.spy(new RoomDBAccess(urlBase)); // initialize RoomDBAccess and a spy to track it
    }

    @Test
    public void testGetRooms() {
        // here we are just checking that a call to getRooms is a call to getRows
        List<RoomDBModel> expectedRooms = Collections.singletonList(mockRoomDBModel);
        doReturn(expectedRooms).when(roomDBAccess).getRows();
        List<RoomDBModel> actualRooms = roomDBAccess.getRooms();
        assertEquals(expectedRooms, actualRooms);
    }

    @Test
    public void testAddARoom() {
        // here we are just checking that a call to addARoom is a call to modifyARow
        doNothing().when(roomDBAccess).modifyARow(anyInt(), any());
        roomDBAccess.addARoom(mockRoomDBModel);
        verify(roomDBAccess, times(1)).modifyARow(anyInt(), any());
    }

    @Test
    public void testJsonToObject() {
        // prepare a JsonObject with room data
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", 1);
        jsonObject.addProperty("host", 100);
        jsonObject.addProperty("name", "Test Room");
        jsonObject.addProperty("activeUsers", "[101, 102, 103]");
        jsonObject.addProperty("messageHistory", "Some messages");
        JsonObject mainObject = new JsonObject();
        mainObject.add("room", jsonObject);

        // call the method (jsonToObject)
        RoomDBModel roomDBModel = roomDBAccess.jsonToObject(mainObject);

        // check that the object returned by jsonToObject has the expected properties
        assertEquals(1, roomDBModel.getRoomID());
        assertEquals("Test Room", roomDBModel.getRoomName());
        assertEquals(100, roomDBModel.getHostID());
        assertEquals(List.of(101, 102, 103), roomDBModel.getActiveUserIDs());
        assertEquals("Some messages", roomDBModel.getMessageHistory());
    }

    @Test
    public void testObjectToJson() {
        // prepare the mock room object
        when(mockRoomDBModel.getHostID()).thenReturn(100);
        when(mockRoomDBModel.getRoomName()).thenReturn("Test Room");
        when(mockRoomDBModel.getActiveUserIDs()).thenReturn(new ArrayList<>());
        when(mockRoomDBModel.getMessageHistory()).thenReturn("Some messages");

        // prepare the expected JsonObject
        JsonObject expectedJson = new JsonObject();
        JsonObject roomObject = new JsonObject();
        roomObject.addProperty("host", 100);
        roomObject.addProperty("name", "Test Room");
        roomObject.addProperty("activeUsers", "[]");
        roomObject.addProperty("messageHistory", "Some messages");
        expectedJson.add("room", roomObject);

        // call the method (objectToJson)
        JsonObject actualJson = roomDBAccess.objectToJson(mockRoomDBModel);

        // check that the object returned by objectToJson is the expected JsonObject
        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testGetRoute() {
        // test if the getRoute method returns the expected string
        assertEquals("rooms", roomDBAccess.getRoute());
    }
}

