package database;

import models.UserDBModel;

import java.util.List;

public interface UserDBGateway {
    List<UserDBModel> loadUsers();
    void saveNewUser(UserDBModel request);
    UserDBModel fetchUser(Integer userID);
}
