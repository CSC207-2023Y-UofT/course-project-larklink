package views_and_drivers.database;

import org.junit.jupiter.api.Test;
import use_cases_and_adapters.UserDBModel;
import java.util.*;

class UserDBAccessTest {

    // We omit the database methods because they are quite simple and are difficult to mock

    @Test
    void testUserWrapper() {
        UserDBModel userDBModel = new UserDBModel(1, "username", "password");
        UserDBAccess.UserWrapper userWrapper = new UserDBAccess.UserWrapper(userDBModel);

        assert userWrapper.user == userDBModel;
    }

    @Test
    void testUserListWrapper() {
        UserDBModel userDBModel = new UserDBModel(1, "username", "password");
        List<UserDBModel> users = Collections.singletonList(userDBModel);

        UserDBAccess.UserListWrapper userListWrapper = new UserDBAccess.UserListWrapper();
        userListWrapper.users = users;

        assert userListWrapper.users.size() == 1;
        assert userListWrapper.users.get(0) == userDBModel;
    }
}
