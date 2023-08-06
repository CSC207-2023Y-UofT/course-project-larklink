package database_and_drivers;

import database_and_drivers.converters.RoomConverter;
import use_cases_and_adapters.RoomDBModel;

import org.junit.jupiter.api.*;
import com.google.gson.*;
import org.mockito.*;

import java.io.IOException;
import java.util.*;

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
        Mockito.when(httpClient.performGETRequest(dbAccess.getRoute(), null)).thenReturn(gson.toJson(mockResponse));

        // when conversion is attempted, return the mock room
        Mockito.when(converter.toObject(Mockito.any(JsonObject.class))).thenReturn(mockRoom);

        // perform the test
        List<RoomDBModel> rows = dbAccess.getRows();
        assert rows.size() == 1;
        assert rows.get(0) == mockRoom; // check if the row we got is equal to the mock row
    }

    @Test
    public void testGetARow() throws IOException {
        // when a GET request is performed, return the mock response (a single room in this case)
        Mockito.when(httpClient.performGETRequest(dbAccess.getRoute(), 1)).thenReturn(gson.toJson(mockResponse));

        // when conversion is attempted, return the mock room
        Mockito.when(converter.toObject(Mockito.any(JsonObject.class))).thenReturn(mockRoom);

        // perform the test
        RoomDBModel row = dbAccess.getARow(1);
        assert row == mockRoom; // check if the fetched row matches the mock row
    }

    @Test
    public void testUpdateARow() throws IOException {
        Mockito.when(converter.toJson(Mockito.any(RoomDBModel.class))).thenReturn(new JsonObject()); // mocking conversion

        // perform the test
        dbAccess.updateARow(1, mockRoom);
        // check if the PUT request was performed with the correct parameters
        Mockito.verify(httpClient).performPUTRequest(dbAccess.getRoute(), 1, mockResponse.toString());
    }

    @Test
    public void testGetRowsIOException() throws IOException {
        // when a GET request is performed, throw an IOException
        Mockito.when(httpClient.performGETRequest(dbAccess.getRoute(), null)).thenThrow(new IOException());

        // perform the test
        List<RoomDBModel> rows = dbAccess.getRows();

        // check that we got no rows
        assert rows.size() == 0;
    }

    @Test
    public void testGetARowIOException() throws IOException {
        // when a GET request is performed, throw an IOException
        Mockito.when(httpClient.performGETRequest(dbAccess.getRoute(), 1)).thenThrow(new IOException());

        // perform the test
        RoomDBModel row = dbAccess.getARow(1);

        // check that the fetched row is null
        assert row == null;
    }

    @Test
    public void testUpdateARowIOException() throws IOException {
        Mockito.when(converter.toJson(Mockito.any(RoomDBModel.class))).thenReturn(new JsonObject()); // mocking conversion

        // when a PUT request is performed, throw an IOException
        Mockito.doThrow(new IOException()).when(httpClient).performPUTRequest(dbAccess.getRoute(), 1, mockResponse.toString());

        // perform the test (exception is caught, so no need to assert anything)
        dbAccess.updateARow(1, mockRoom);
    }
}
