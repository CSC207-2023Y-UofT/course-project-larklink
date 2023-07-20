package signup_and_login;

import java.util.List;

public interface UserDBGateway {
    List<UserModel> loadUsers();
    void saveNewUser(UserModel request);
}
