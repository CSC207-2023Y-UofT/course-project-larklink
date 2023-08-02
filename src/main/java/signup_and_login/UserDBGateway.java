package signup_and_login;

import database.UserDBModel;

import java.util.List;

/**
 * A DB gateway for signup and login use case.
 */
public interface UserDBGateway  {
    List<UserDBModel> getUsers();
    void addAUser(UserDBModel request);
}
