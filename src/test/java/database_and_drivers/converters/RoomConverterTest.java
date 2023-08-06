package database_and_drivers.converters;

import com.google.gson.JsonObject;
import use_cases_and_adapters.RoomDBModel;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.util.List;

public class RoomConverterTest {

    @Mock
    private RoomDBModel mockRoomDBModel;
    private RoomConverter roomConverter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        roomConverter = new RoomConverter();
    }

    @Test
    public void testToObject() {
        // prepare a JsonObject with room data
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", 1);
        jsonObject.addProperty("host", 100);
        jsonObject.addProperty("name", "Test Room");
        jsonObject.addProperty("activeUsers", "[101, 102, 103]");
        jsonObject.addProperty("messageHistory", "messages");
        JsonObject mainObject = new JsonObject();
        mainObject.add("room", jsonObject);

        // call the method (jsonToObject)
        RoomDBModel roomDBModel = roomConverter.toObject(mainObject);

        // check that the object returned by jsonToObject has the expected properties
        assert roomDBModel.getRoomID() == 1;
        assert roomDBModel.getRoomName().equals("Test Room");
        assert roomDBModel.getHostID() == 100;
        assert roomDBModel.getActiveUserIDs().equals(List.of(101, 102, 103));
        assert roomDBModel.getMessageHistory().equals("messages");
    }

    @Test
    public void testToJson() {
        // prepare the mock room object
        Mockito.when(mockRoomDBModel.getHostID()).thenReturn(100);
        Mockito.when(mockRoomDBModel.getRoomName()).thenReturn("Test Room");
        Mockito.when(mockRoomDBModel.getActiveUserIDs()).thenReturn(List.of(1, 2, 3));
        Mockito.when(mockRoomDBModel.getMessageHistory()).thenReturn("Some messages");

        // prepare the expected JsonObject
        JsonObject expectedJson = new JsonObject();
        JsonObject roomObject = new JsonObject();
        roomObject.addProperty("host", 100);
        roomObject.addProperty("name", "Test Room");
        roomObject.addProperty("activeUsers", "[1,2,3]");
        roomObject.addProperty("messageHistory", "Some messages");
        expectedJson.add("room", roomObject);

        // call the method (objectToJson)
        JsonObject actualJson = roomConverter.toJson(mockRoomDBModel);

        // check that the object returned by objectToJson is the expected JsonObject
        assert expectedJson.equals(actualJson);
    }
}