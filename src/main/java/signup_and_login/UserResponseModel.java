package signup_and_login;

public class UserResponseModel {
    public final String userID;
    public final Boolean wasSuccess;

    public UserResponseModel(String userID, Boolean wasSuccess) {
        this.userID = userID;
        this.wasSuccess = wasSuccess;
    }
}
