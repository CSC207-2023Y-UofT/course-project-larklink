package use_case.signup_and_login;

import database.UserDBModel;

import java.util.List;

public interface UserDBGateway  {
    List<UserDBModel> getUsers();
    void addAUser(UserDBModel request);
}
