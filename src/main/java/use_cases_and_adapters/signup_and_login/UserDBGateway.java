package use_cases_and_adapters.signup_and_login;

import use_cases_and_adapters.UserDBModel;

import java.util.List;

/**
 * A DB gateway for signup and login use case.
 * This class is an abstraction layer between UserSignupInteractor/UserLoginInteractor and UserDBAccess.
 */
public interface UserDBGateway  {

    /**
     * Retrieves a list of all users from the database.
     *
     * @return a List of UserDBModel objects representing all users in the database,
     * or an empty list if an error occurred.
     */

    List<UserDBModel> getUsers();

    /**
     * Adds a new user to the database.
     *
     * @param request a UserDBModel object containing the data for the new user.
     */
    void addAUser(UserDBModel request);
}
