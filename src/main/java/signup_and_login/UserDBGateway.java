package signup_and_login;

import java.util.List;

public interface UserDBGateway {
    List<UserDBModel> loadUsers();
    void saveNewUser(UserDBModel request);
    UserDBModel fetchUser(int userID);
}
