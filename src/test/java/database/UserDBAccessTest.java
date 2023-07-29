package database;

import com.google.gson.JsonObject;
import models.UserDBModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserDBAccessTest {

    private UserDBAccess userDBAccess;

    @Mock
    private UserDBModel mockUserDBModel;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        String urlBase = "http://localhost:8080/api/";
        userDBAccess = Mockito.spy(new UserDBAccess(urlBase)); // initialize UserDBAccess and a spy to track it
    }

    @Test
    public void testGetUsers() {
        // here we are just checking that a call to getUsers is a call to getRows
        List<UserDBModel> expectedUsers = Collections.singletonList(mockUserDBModel);
        doReturn(expectedUsers).when(userDBAccess).getRows();
        List<UserDBModel> actualUsers = userDBAccess.getUsers();
        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void testAddAUser() {
        // here we are just checking that a call to addAUser is a call to addARow
        doNothing().when(userDBAccess).addARow(anyInt(), any());
        userDBAccess.addAUser(mockUserDBModel);
        verify(userDBAccess, times(1)).addARow(anyInt(), any());
    }

    @Test
    public void testJsonToObject() {
        // prepare a JsonObject with user data
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", 1);
        jsonObject.addProperty("username", "testUser");
        jsonObject.addProperty("password", "testPassword");
        JsonObject mainObject = new JsonObject();
        mainObject.add("user", jsonObject);

        // call the method (jsonToObject)
        UserDBModel userDBModel = userDBAccess.jsonToObject(mainObject);

        // check that the object returned by jsonToObject has the expected properties
        assertEquals(1, userDBModel.getUserID());
        assertEquals("testUser", userDBModel.getUsername());
        assertEquals("testPassword", userDBModel.getPassword());
    }

    @Test
    public void testObjectToJson() {
        // prepare the mock user object
        when(mockUserDBModel.getUsername()).thenReturn("testUser");
        when(mockUserDBModel.getPassword()).thenReturn("testPassword");

        // prepare the expected JsonObject
        JsonObject expectedJson = new JsonObject();
        JsonObject userObject = new JsonObject();
        userObject.addProperty("username", "testUser");
        userObject.addProperty("password", "testPassword");
        expectedJson.add("user", userObject);

        // call the method (objectToJson)
        JsonObject actualJson = userDBAccess.objectToJson(mockUserDBModel);

        // check that the object returned by objectToJson is the expected JsonObject
        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testGetRoute() {
        // test if the getRoute method returns the expected string
        assertEquals("users", userDBAccess.getRoute());
    }
}
