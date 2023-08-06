package database_and_drivers;

import database_and_drivers.converters.UserConverter;
import use_cases_and_adapters.UserDBModel;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.util.*;

public class UserDBAccessTest {

    @Mock
    private UserDBModel mockUserDBModel;

    @Mock
    private HttpClient mockHttpClient;

    private UserDBAccess userDBAccess;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // initialize UserDBAccess and a spy to track it
        userDBAccess = Mockito.spy(new UserDBAccess(mockHttpClient, new UserConverter()));
    }

    @Test
    public void testGetUsers() {
        // here we are just checking that a call to getUsers is a call to getRows
        List<UserDBModel> expectedUsers = Collections.singletonList(mockUserDBModel);
        Mockito.doReturn(expectedUsers).when(userDBAccess).getRows();
        List<UserDBModel> actualUsers = userDBAccess.getUsers();
        assert expectedUsers == actualUsers;
    }

    @Test
    public void testAddAUser() {
        // here we are just checking that a call to addAUser is a call to updateARow
        Mockito.doNothing().when(userDBAccess).updateARow(Mockito.anyInt(), Mockito.any());
        userDBAccess.addAUser(mockUserDBModel);
        Mockito.verify(userDBAccess, Mockito.times(1)).updateARow(Mockito.anyInt(), Mockito.any());
    }

    @Test
    public void testGetRoute() {
        assert userDBAccess.getRoute().equals("users");
    }
}
