package use_cases_and_adapters.user_signup;

import entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import use_cases_and_adapters.UserDBModel;
import use_cases_and_adapters.signup_and_login.UserDBGateway;
import use_cases_and_adapters.signup_and_login.UserModel;
import use_cases_and_adapters.signup_and_login.user_signup.UserSignupInteractor;
import use_cases_and_adapters.signup_and_login.user_signup.UserSignupOutputBoundary;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

/**
 * This class tests UserSignupInteractor.
 */
public class UserSignupInteractorTest {
    @Mock
    private UserSignupOutputBoundary presenter;
    @Mock
    private UserDBGateway database;
    private UserSignupInteractor interactor;
    private String oldUsername;
    private List<UserDBModel> users;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        interactor = new UserSignupInteractor(database, presenter);

        int oldUserID = 2;
        oldUsername = "oldUsername";
        String oldHashedPassword = User.hashPassword("oldPassword");
        users = List.of(new UserDBModel(oldUserID, oldUsername, oldHashedPassword));
    }

    /**
     * Tests handleUserSignup for the case when username already exists.
     */
    @Test
    public void testHandleUserSignupFailure() {
        when(database.getUsers()).thenReturn(users);

        UserModel failedUser = new UserModel(oldUsername, "newPassword");
        interactor.handleUserSignup(failedUser);

        // checks that presenter calls prepareUsernameExistsView exactly once
        verify(presenter, times(1)).prepareUsernameExistsView();
        // checks that presenter never calls prepareJoinOrHostView
        verify(presenter, never()).prepareJoinOrHostView();
        // checks that database never calls addAUser so no new user is added to database
        verify(database, never()).addAUser(any(UserDBModel.class));
    }

    /**
     * Tests handleUserSignup for successful user signup.
     */
    @Test
    public void testHandleUserSignupSuccess() {
        when(database.getUsers()).thenReturn(users);

        UserModel newUser = new UserModel("newUsername", "newPassword");
        interactor.handleUserSignup(newUser);

        ArgumentCaptor<UserDBModel> captor = ArgumentCaptor.forClass(UserDBModel.class);
        // checks that database calls addAUser method exactly once with the correct UserModel object
        verify(database, times(1)).addAUser(captor.capture());

        UserDBModel addedUser = captor.getValue(); // get the argument that was passed to addAUser

        // checks that newly added user got correct userID
        assert addedUser.getUserID() == 3; // since oldUserID == 2

        // checks that correct username and password are saved into database
        assert addedUser.getUsername().equals(newUser.getUsername());
        assert User.checkPassword(newUser.getPassword(), addedUser.getPassword());

        // checks that presenter calls prepareJoinOrHostView exactly once
        verify(presenter, times(1)).prepareJoinOrHostView();
        // checks that presenter never calls prepareUsernameExistsView
        verify(presenter, never()).prepareUsernameExistsView();
    }
}