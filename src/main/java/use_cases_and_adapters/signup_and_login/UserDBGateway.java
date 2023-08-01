package use_cases_and_adapters.signup_and_login;

import use_cases_and_adapters.UserDBModel;

import java.util.List;

public interface UserDBGateway  {
    List<UserDBModel> getUsers();
    void addAUser(UserDBModel request);
}
