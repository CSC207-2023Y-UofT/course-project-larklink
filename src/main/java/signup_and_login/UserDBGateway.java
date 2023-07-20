package signup_and_login;

import java.util.List;

public interface UserDBGateway {
    List<UserRequestModel> loadUsers();
    void saveNewUser(UserRequestModel request);
}
