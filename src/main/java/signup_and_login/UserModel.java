package signup_and_login;

public class UserModel {
    private int userID;
    private final String username;
    private String password;

    public UserModel(int userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    public int getUserID() {
        return this.userID;
    }
    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
}