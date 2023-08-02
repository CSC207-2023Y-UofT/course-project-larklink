package use_cases_and_adapters.signup_and_login;

import use_cases_and_adapters.UserDBModel;
import entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.mockito.Mockito.*;

public class UserInteractorTest {

    private UserInteractor userInteractor;

    @Mock
    private UserOutputBoundary presenter;

    @Mock
    private UserDBGateway database;

    private String testUsername;
    private String testPassword;
    private List<UserDBModel> users;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userInteractor = new UserInteractor(presenter, database);

        // initialize shared variables
        int testUserID = 2;
        testUsername = "testUser";
        testPassword = "testPassword";
        String hashedPassword = User.hashPassword(testPassword);
        users = List.of(new UserDBModel(testUserID, testUsername, hashedPassword));
    }

    @Test
    public void testHandleUser_existingUser() {
        when(database.getUsers()).thenReturn(users);

        UserModel existingUser = new UserModel(testUsername, testPassword);
        userInteractor.handleUser(existingUser);

        verify(presenter).prepareJoinOrHostView();
        verify(database, never()).addAUser(any(UserDBModel.class));
    }

    @Test
    public void testHandleUser_invalidPassword() {
        when(database.getUsers()).thenReturn(users);

        UserModel invalidUser = new UserModel(testUsername, "incorrectPassword");
        userInteractor.handleUser(invalidUser);

        verify(presenter).prepareInvalidCredentialsView();
        verify(database, never()).addAUser(any(UserDBModel.class));
    }

    @Test
    public void testHandleUser_newUser() {
        when(database.getUsers()).thenReturn(users);

        UserModel newUser = new UserModel("newUser", "newPassword");
        userInteractor.handleUser(newUser);

        ArgumentCaptor<UserDBModel> captor = ArgumentCaptor.forClass(UserDBModel.class);
        verify(database).addAUser(captor.capture()); // check that we did add a user to the database
        UserDBModel addedUser = captor.getValue(); // get the argument that was passed to addAUser

        assert addedUser.getUserID() == 3; // since testUserID == 2
        assert addedUser.getUsername().equals(newUser.getUsername()); // check that we passed in the right username
        assert User.checkPassword(newUser.getPassword(), addedUser.getPassword()); // check that we passed in the right password
        verify(presenter).prepareJoinOrHostView();
    }
}
