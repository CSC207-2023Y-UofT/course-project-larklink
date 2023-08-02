package use_cases_and_adapters.signup_and_login;

import use_cases_and_adapters.UserDBModel;

import java.util.List;

/**
 * A DB gateway for signup and login use case.
 */
public interface UserDBGateway  {
    List<UserDBModel> getUsers();
    void addAUser(UserDBModel request);
}
