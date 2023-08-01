package database;

import com.google.gson.JsonObject;
import signup_and_login.UserDBGateway;

import java.util.List;

public class UserDBAccess extends DBAccess<UserDBModel> implements UserDBGateway {

    public UserDBAccess(String urlBase) {
        super(urlBase);
    }

    @Override
    public List<UserDBModel> getUsers() {
        return getRows();
    }

    @Override
    public void addAUser(UserDBModel request) {
        updateARow(request.getUserID(), request);
    }

    @Override
    protected UserDBModel jsonToObject(JsonObject jsonObject) {
        int userId = jsonObject.get("id").getAsInt();
        String username = jsonObject.get("username").getAsString();
        String password = jsonObject.get("password").getAsString();

        return new UserDBModel(userId, username, password);
    }

    @Override
    protected JsonObject objectToJson(UserDBModel model) {
        JsonObject userObject = new JsonObject();
        userObject.addProperty("username", model.getUsername());
        userObject.addProperty("password", model.getPassword());

        JsonObject mainObject = new JsonObject();
        mainObject.add("user", userObject);

        return mainObject;
    }

    @Override
    protected String getRoute() {
        return "users";
    }
}
