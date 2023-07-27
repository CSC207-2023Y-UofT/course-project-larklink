package database;

import models.UserDBModel;

import java.util.List;

public interface UserDBGateway {
    List<UserDBModel> retrieveEveryUser();
    void addAUser(UserDBModel request);
}
