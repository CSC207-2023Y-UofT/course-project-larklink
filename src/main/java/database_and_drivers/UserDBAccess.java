package database_and_drivers;

import kong.unirest.Unirest;
import use_cases_and_adapters.UserDBModel;
import use_cases_and_adapters.signup_and_login.UserDBGateway;

import java.util.List;


/**
 * A DB access class for our "users" database that uses and returns UserDBModel objects.
 */
public class UserDBAccess implements UserDBGateway {
    private static final String ROUTE = "users";

    /**
     * Retrieves a list of all users from the database.
     *
     * @return a List of UserDBModel objects representing all users in the database,
     * or an empty list if an error occurred.
     */
    @Override
    public List<UserDBModel> getUsers() {
        return Unirest.get(ROUTE).asObject(UserListWrapper.class).getBody().users;
    }

    /**
     * Adds a new user to the database.
     *
     * @param user a UserDBModel object containing the data for the new user.
     */
    @Override
    public void addAUser(UserDBModel user) {
        Unirest.post(ROUTE).header("Content-Type", "application/json").body(new UserWrapper(user)).asEmpty();
    }

    /**
     * A wrapper class used to get around Json formatting
     */
    static class UserWrapper {
        protected UserDBModel user;
        public UserWrapper(UserDBModel user) {
            this.user = user;
        }
    }

    /**
     * A wrapper class used to get around Json formatting
     */
    static class UserListWrapper {
        protected List<UserDBModel> users;
    }
}
