package database_and_drivers.converters;

import com.google.gson.JsonObject;
import use_cases_and_adapters.UserDBModel;
import org.junit.jupiter.api.*;
import org.mockito.*;

public class UserConverterTest {

    @Mock
    private UserDBModel mockUserDBModel;
    private UserConverter userConverter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userConverter = new UserConverter();
    }

    @Test
    public void testToObject() {
        // prepare a JsonObject with user data
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", 1);
        jsonObject.addProperty("username", "testUser");
        jsonObject.addProperty("password", "testPassword");

        // call the method (toObject)
        UserDBModel userDBModel = userConverter.toObject(jsonObject);

        // check that the object returned by toObject has the expected properties
        assert userDBModel.getUserID() == 1;
        assert userDBModel.getUsername().equals("testUser");
        assert userDBModel.getPassword().equals("testPassword");
    }

    @Test
    public void testToJson() {
        // prepare the mock user object
        Mockito.when(mockUserDBModel.getUsername()).thenReturn("testUser");
        Mockito.when(mockUserDBModel.getPassword()).thenReturn("testPassword");

        // prepare the expected JsonObject
        JsonObject expectedJson = new JsonObject();
        JsonObject userObject = new JsonObject();
        userObject.addProperty("username", "testUser");
        userObject.addProperty("password", "testPassword");
        expectedJson.add("user", userObject);

        // call the method (toJson)
        JsonObject actualJson = userConverter.toJson(mockUserDBModel);

        // check that the object returned by objectToJson is the expected JsonObject
        assert actualJson.equals(expectedJson);
    }
}