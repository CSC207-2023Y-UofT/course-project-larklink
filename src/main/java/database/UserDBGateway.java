package database;

import models.UserDBModel;

import java.util.List;

public interface UserDBGateway {
    List<UserDBModel> getUsers();
    void addAUser(UserDBModel request);
}
