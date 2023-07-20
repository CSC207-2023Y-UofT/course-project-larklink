package signup_and_login;

public class UserRequestModel {
    private final String username;
    private final String password;

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