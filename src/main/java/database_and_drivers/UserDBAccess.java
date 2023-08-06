package database_and_drivers;

import database_and_drivers.converters.UserConverter;
import use_cases_and_adapters.UserDBModel;
import use_cases_and_adapters.signup_and_login.UserDBGateway;

import java.util.List;


/**
 * The implementation of the DBAccess abstract class for UserDBModel objects.
 */
public class UserDBAccess extends DBAccess<UserDBModel> implements UserDBGateway {

    /**
     * Constructs a new UserDBAccess object with the given HttpClient and UserConverter instances.
     *
     * @param httpClient The HttpClient instance responsible for handling HTTP requests to the API.
     * @param converter  The UserConverter instance responsible for switching between JSON data to User objects.
     */
    public UserDBAccess(HttpClient httpClient, UserConverter converter) {
        super(httpClient,converter);
    }

    /**
     * Retrieves a list of all users from the database.
     *
     * @return a List of UserDBModel objects representing all users in the database,
     * or an empty list if an error occurred.
     */
    @Override
    public List<UserDBModel> getUsers() {
        return getRows();
    }

    /**
     * Adds a new user to the database.
     *
     * @param request a UserDBModel object containing the data for the new user.
     */
    @Override
    public void addAUser(UserDBModel request) {
        updateARow(request.getUserID(), request);
    }

    /**
     * Returns the route for the User database table.
     * This is used by the superclass methods to construct the URLs for the HTTP requests.
     *
     * @return the route for the User database table.
     */
    @Override
    protected String getRoute() {
        return "users";
    }
}
