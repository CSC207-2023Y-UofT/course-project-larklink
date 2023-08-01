package use_cases.signup_and_login;

import use_cases.UserDBModel;

import java.util.List;

public interface UserDBGateway  {
    List<UserDBModel> getUsers();
    void addAUser(UserDBModel request);
}
