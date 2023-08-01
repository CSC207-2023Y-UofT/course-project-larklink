package database;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
import static org.mockito.Mockito.when;

public class DBAccessTest {

    @Mock // mocking HttpClient to simulate network calls
    private HttpClient httpClient;

    @Mock // mocking RoomConverter to simulate the conversion process
    private RoomConverter converter;

    private DBAccess<RoomDBModel> dbAccess;

    private RoomDBModel mockRoom;

    private JsonObject mockResponse;


    private final Gson gson = new Gson();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        dbAccess = new RoomDBAccess(httpClient, converter);
        mockRoom = new RoomDBModel(1, "Room1", 2, Collections.singletonList(3), "msg history");
        mockResponse = new JsonObject();
    }

    @Test
    public void testGetRows() throws IOException {
        // mocking JSON response from server
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonObject());
        mockResponse.add(dbAccess.getRoute(), jsonArray);

        // when a GET request is performed, return the mock response (a list of rooms in this case)
        when(httpClient.performGETRequest(dbAccess.getRoute(), null)).thenReturn(gson.toJson(mockResponse));

        // when conversion is attempted, return the mock room
        when(converter.toObject(Mockito.any(JsonObject.class))).thenReturn(mockRoom);

        // perform the test
        List<RoomDBModel> rows = dbAccess.getRows();
        assertEquals(1, rows.size()); // check if we got exactly one row
        assertEquals(mockRoom, rows.get(0)); // check if the row we got is equal to the mock row
    }

    @Test
    public void testGetARow() throws IOException {
        // when a GET request is performed, return the mock response (a single room in this case)
        when(httpClient.performGETRequest(dbAccess.getRoute(), 1)).thenReturn(gson.toJson(mockResponse));

        // when conversion is attempted, return the mock room
        when(converter.toObject(Mockito.any(JsonObject.class))).thenReturn(mockRoom);

        // perform the test
        RoomDBModel row = dbAccess.getARow(1);
        assertEquals(mockRoom, row); // check if the fetched row matches the mock row
    }

    @Test
    public void testUpdateARow() throws IOException {
        when(converter.toJson(Mockito.any(RoomDBModel.class))).thenReturn(new JsonObject()); // mocking conversion

        // perform the test
        dbAccess.updateARow(1, mockRoom);
        // check if the PUT request was performed with the correct parameters
        Mockito.verify(httpClient).performPUTRequest(dbAccess.getRoute(), 1, mockResponse.toString());
    }
}
