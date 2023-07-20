package join_room_use_case;

import entity.User;

public class UserModel {
    private User user;

    public UserModel(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
