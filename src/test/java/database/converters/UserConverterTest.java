package database.converters;

import com.google.gson.JsonObject;
import database.UserDBModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
        assertEquals(1, userDBModel.getUserID());
        assertEquals("testUser", userDBModel.getUsername());
        assertEquals("testPassword", userDBModel.getPassword());
    }

    @Test
    public void testToJson() {
        // prepare the mock user object
        when(mockUserDBModel.getUsername()).thenReturn("testUser");
        when(mockUserDBModel.getPassword()).thenReturn("testPassword");

        // prepare the expected JsonObject
        JsonObject expectedJson = new JsonObject();
        JsonObject userObject = new JsonObject();
        userObject.addProperty("username", "testUser");
        userObject.addProperty("password", "testPassword");
        expectedJson.add("user", userObject);

        // call the method (toJson)
        JsonObject actualJson = userConverter.toJson(mockUserDBModel);

        // check that the object returned by objectToJson is the expected JsonObject
        assertEquals(expectedJson, actualJson);
    }
}