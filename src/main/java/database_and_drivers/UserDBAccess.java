package database_and_drivers;

import kong.unirest.Unirest;
import use_cases_and_adapters.UserDBModel;
import use_cases_and_adapters.signup_and_login.UserDBGateway;

import java.util.List;


/**
 * The implementation of the DBAccess abstract class for UserDBModel objects.
 */
public class UserDBAccess implements  UserDBGateway {
    private static final String ROUTE = "users";
    /**
     * Retrieves a list of all users from the database.
     *
     * @return a List of UserDBModel objects representing all users in the database,
     * or an empty list if an error occurred.
     */
    @Override
    public List<UserDBModel> getUsers() {
        UserResponseWrapper response = Unirest.get(ROUTE).asObject(UserResponseWrapper.class).getBody();
        return response.users;
    }

    /**
     * Adds a new user to the database.
     *
     * @param user a UserDBModel object containing the data for the new user.
     */
    @Override
    public void addAUser(UserDBModel user) {
        Unirest.post(ROUTE)
                .header("Content-Type", "application/json")
                .body(new UserRequestWrapper(user))
                .asEmpty();
    }

    private static class UserRequestWrapper {
        protected UserDBModel user;
        public UserRequestWrapper(UserDBModel user) {
            this.user = user;
        }
    }

    private static class UserResponseWrapper {
        protected List<UserDBModel> users;
    }
}
