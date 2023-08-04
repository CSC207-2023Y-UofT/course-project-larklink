package database_and_drivers;

import database_and_drivers.converters.UserConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import use_cases_and_adapters.UserDBModel;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserDBAccessTest {

    @Mock
    private UserDBModel mockUserDBModel;

    @Mock
    private HttpClient mockHttpClient;

    private UserDBAccess userDBAccess;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userDBAccess = Mockito.spy(new UserDBAccess(mockHttpClient, new UserConverter())); // initialize UserDBAccess and a spy to track it
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
        // here we are just checking that a call to addAUser is a call to updateARow
        doNothing().when(userDBAccess).updateARow(anyInt(), any());
        userDBAccess.addAUser(mockUserDBModel);
        verify(userDBAccess, times(1)).updateARow(anyInt(), any());
    }

    @Test
    public void testGetRoute() {
        assertEquals("users", userDBAccess.getRoute());
    }
}
