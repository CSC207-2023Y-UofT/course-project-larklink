package signup_and_login;

import database.UserDBGateway;
import models.UserModel;
import models.UserDBModel;
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

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userInteractor = new UserInteractor(presenter, database);
    }

    @Test
    public void testHandleUser_existingUser() {
        String testUsername = "testUser";
        String testPassword = "testPassword";
        String hashedPassword = User.hashPassword(testPassword);
        List<UserDBModel> users = List.of(new UserDBModel(2, testUsername, hashedPassword));
        when(database.getUsers()).thenReturn(users);

        userInteractor.handleUser(new UserModel(testUsername, testPassword));

        verify(presenter).prepareJoinOrHostView(2);
        verify(database, never()).addAUser(any(UserDBModel.class));
    }

    @Test
    public void testHandleUser_invalidPassword() {
        String testUsername = "testUser";
        String correctPassword = "correctPassword";
        String incorrectPassword = "incorrectPassword";
        String hashedPassword = User.hashPassword(correctPassword);
        List<UserDBModel> users = List.of(new UserDBModel(2, testUsername, hashedPassword));
        when(database.getUsers()).thenReturn(users);

        userInteractor.handleUser(new UserModel(testUsername, incorrectPassword));

        verify(presenter).prepareInvalidCredentialsView();
        verify(database, never()).addAUser(any(UserDBModel.class));
    }

    @Test
    public void testHandleUser_newUser() {
        String testUsername = "newUser";
        String testPassword = "newPassword";
        List<UserDBModel> users = List.of(new UserDBModel(2, "existingUser", User.hashPassword("existingPassword")));
        when(database.getUsers()).thenReturn(users);

        userInteractor.handleUser(new UserModel(testUsername, testPassword));

        ArgumentCaptor<UserDBModel> captor = ArgumentCaptor.forClass(UserDBModel.class);
        verify(database).addAUser(captor.capture());
        UserDBModel addedUser = captor.getValue();

        assert addedUser.getUserID() == 3;
        assert addedUser.getUsername().equals(testUsername);
        assert User.checkPassword(testPassword, addedUser.getPassword());
        verify(presenter).prepareJoinOrHostView(3);
    }
}
