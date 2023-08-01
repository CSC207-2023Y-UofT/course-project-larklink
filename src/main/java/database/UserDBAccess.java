package database;

import database.converters.UserConverter;
import signup_and_login.UserDBGateway;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * UserDBAccess is a specific implementation of the DBAccess abstract class for UserDBModel objects.
 * This class implements UserDBGateway interface and provides concrete methods for those interfaces.
 * UserDBAccess makes use of a UserConverter for converting between UserDBModel objects and their database representation,
 * and an HttpClient to perform HTTP requests.
 */
public class UserDBAccess extends DBAccess<UserDBModel> implements UserDBGateway {

    public UserDBAccess(HttpClient httpClient, UserConverter converter) {
        super(httpClient,converter);
    }

    /**
     * Retrieves a list of all users from the database.
     * If an IOException occurs during the operation, an error message is displayed to the user, and an empty list is returned.
     *
     * @return a List of UserDBModel objects representing all users in the database, or an empty list if an error occurred.
     */
    @Override
    public List<UserDBModel> getUsers() {
        try {
             return getRows();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "There was an error connecting to the database!");
        }
        return new ArrayList<>();
    }

    /**
     * Adds a new user to the database.
     * If an IOException occurs during the operation, an error message is displayed to the user.
     *
     * @param request a UserDBModel object containing the data for the new user.
     */
    @Override
    public void addAUser(UserDBModel request) {
        try {
            updateARow(request.getUserID(), request);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "There was an error connecting to the database!");
        }
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
