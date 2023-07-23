package join_room_use_case;

import java.util.List;

public interface UserDBGateway {
    List<UserDBModel> loadUsers();
}
