package use_cases_and_adapters.user_login;

import entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import use_cases_and_adapters.UserDBModel;
import use_cases_and_adapters.signup_and_login.UserDBGateway;
import use_cases_and_adapters.signup_and_login.UserModel;
import use_cases_and_adapters.signup_and_login.user_login.UserLoginInteractor;
import use_cases_and_adapters.signup_and_login.user_login.UserLoginOutputBoundary;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

/**
 * This class tests UserLoginInteractor.
 */
public class UserLoginInteractorTest {
    @Mock
    private UserLoginOutputBoundary presenter;
    @Mock
    private UserDBGateway database;
    private UserLoginInteractor interactor;
    private Integer testUserID;
    private String testUsername;
    private String testPassword;
    private List<UserDBModel> users;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        interactor = new UserLoginInteractor(database, presenter);
        testUserID = 2;
        testUsername = "kim";
        testPassword = "12345678";
        String hashedPassword = User.hashPassword(testPassword);
        users = List.of(new UserDBModel(testUserID, testUsername, hashedPassword));
    }

    /**
     * Tests handleUserLogin for successful user login.
     */
    @Test
    public void testHandleUserLoginSuccess() {
        when(database.getUsers()).thenReturn(users);

        UserModel testUserModel = new UserModel(testUsername, testPassword);
        interactor.handleUserLogin(testUserModel);

        // checks that the user is set by correct userID, username, and password
        assertEquals(testUserID, User.getUserID());
        assertEquals(testUsername, User.getUsername());
        assertTrue(User.checkPassword(testPassword, User.getPassword()));

        // checks that presenter calls prepareJoinOrHostView exactly once
        verify(presenter, times(1)).prepareJoinOrHostView();
        // checks that presenter never calls prepareInvalidUsernameView
        verify(presenter, never()).prepareInvalidUsernameView();
        // checks that presenter never calls prepareInvalidPasswordView
        verify(presenter, never()).prepareInvalidPasswordView();
    }

    /**
     * Tests handleUserLogin for the case when username does not exist.
     */
    @Test
    public void testHandleUserLoginInvalidUsername() {
        when(database.getUsers()).thenReturn(users);

        UserModel invalidUsernameModel = new UserModel("invalidUser", testPassword);
        interactor.handleUserLogin(invalidUsernameModel);

        // checks that presenter never calls prepareJoinOrHostView
        verify(presenter, never()).prepareJoinOrHostView();
        // checks that presenter calls prepareInvalidUsernameView exactly once
        verify(presenter, times(1)).prepareInvalidUsernameView();
        // checks that presenter never calls prepareInvalidPasswordView
        verify(presenter, never()).prepareInvalidPasswordView();
    }

    /**
     * Tests handleUserLogin for the case when password is incorrect.
     */
    @Test
    public void testHandleUserLoginInvalidPassword() {
        when(database.getUsers()).thenReturn(users);

        UserModel invalidPWModel = new UserModel(testUsername, "invalidPW");
        interactor.handleUserLogin(invalidPWModel);

        // checks that presenter never calls prepareJoinOrHostView
        verify(presenter, never()).prepareJoinOrHostView();
        // checks that presenter calls prepareInvalidPasswordView exactly once
        verify(presenter, times(1)).prepareInvalidPasswordView();
        // checks that presenter never calls prepareInvalidUsernameView
        verify(presenter, never()).prepareInvalidUsernameView();
    }
}
