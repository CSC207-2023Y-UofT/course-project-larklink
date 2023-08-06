package use_cases_and_adapters.user_login;

import entities.User;
import org.junit.jupiter.api.*;
import org.mockito.*;
import use_cases_and_adapters.UserDBModel;
import use_cases_and_adapters.signup_and_login.UserDBGateway;
import use_cases_and_adapters.signup_and_login.UserModel;
import use_cases_and_adapters.signup_and_login.user_login.UserLoginInteractor;
import use_cases_and_adapters.signup_and_login.user_login.UserLoginOutputBoundary;
import java.util.List;

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
        Mockito.when(database.getUsers()).thenReturn(users);

        UserModel testUserModel = new UserModel(testUsername, testPassword);
        interactor.handleUserLogin(testUserModel);

        // checks that the user is set by correct userID, username, and password
        assert User.getUserID().equals(testUserID);
        assert User.getUsername().equals(testUsername);
        assert User.checkPassword(testPassword, User.getPassword());

        // checks that presenter calls prepareJoinOrHostView exactly once
        Mockito.verify(presenter, Mockito.times(1)).prepareJoinOrHostView();
        // checks that presenter never calls prepareInvalidUsernameView
        Mockito.verify(presenter, Mockito.never()).prepareInvalidUsernameView();
        // checks that presenter never calls prepareInvalidPasswordView
        Mockito.verify(presenter, Mockito.never()).prepareInvalidPasswordView();
    }

    /**
     * Tests handleUserLogin for the case when username does not exist.
     */
    @Test
    public void testHandleUserLoginInvalidUsername() {
        Mockito.when(database.getUsers()).thenReturn(users);

        UserModel invalidUsernameModel = new UserModel("invalidUser", testPassword);
        interactor.handleUserLogin(invalidUsernameModel);

        // checks that presenter never calls prepareJoinOrHostView
        Mockito.verify(presenter, Mockito.never()).prepareJoinOrHostView();
        // checks that presenter calls prepareInvalidUsernameView exactly once
        Mockito.verify(presenter, Mockito.times(1)).prepareInvalidUsernameView();
        // checks that presenter never calls prepareInvalidPasswordView
        Mockito.verify(presenter, Mockito.never()).prepareInvalidPasswordView();
    }

    /**
     * Tests handleUserLogin for the case when password is incorrect.
     */
    @Test
    public void testHandleUserLoginInvalidPassword() {
        Mockito.when(database.getUsers()).thenReturn(users);

        UserModel invalidPWModel = new UserModel(testUsername, "invalidPassword");
        interactor.handleUserLogin(invalidPWModel);

        // checks that presenter never calls prepareJoinOrHostView
        Mockito.verify(presenter, Mockito.never()).prepareJoinOrHostView();
        // checks that presenter calls prepareInvalidPasswordView exactly once
        Mockito.verify(presenter, Mockito.times(1)).prepareInvalidPasswordView();
        // checks that presenter never calls prepareInvalidUsernameView
        Mockito.verify(presenter, Mockito.never()).prepareInvalidUsernameView();
    }
}
