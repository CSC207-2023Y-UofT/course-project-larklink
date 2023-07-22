package join_room_use_case;

import entities.User;

public class UserModel {
    private final User user;

    public UserModel(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
