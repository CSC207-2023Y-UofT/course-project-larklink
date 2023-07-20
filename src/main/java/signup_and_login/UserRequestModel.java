package signup_and_login;

// note  in our crc model there is a UserDBRequestModel in the same layer that is identical, so I combined them
// TODO: decide if that's a good idea

public class UserRequestModel {
    private String username;
    private String password;

    public UserRequestModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
}