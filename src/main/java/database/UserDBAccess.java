package database;

import com.google.gson.JsonObject;
import models.UserDBModel;

import java.util.List;

public class UserDBAccess extends DBAccess<UserDBModel> implements UserDBGateway {

    public UserDBAccess(String urlBase) {
        super(urlBase);
    }

    @Override
    public UserDBModel fetchUser(Integer userID) {
        return fetch(userID);
    }

    @Override
    public void saveNewUser(UserDBModel request) {
        add(request);
    }

    @Override
    public List<UserDBModel> loadUsers() {
        return loadAll();
    }

    @Override
    protected UserDBModel parseJsonToObject(JsonObject jsonObject) {

        // when we fetch one row we get "user: <information here>" so here we "skip" it
        if (jsonObject.has("user")) {
            jsonObject = jsonObject.get("user").getAsJsonObject();
        }

        int userId = jsonObject.get("id").getAsInt();
        String username = jsonObject.get("username").getAsString();
        String password = jsonObject.get("password").getAsString();

        return new UserDBModel(userId, username, password);
    }

    @Override
    protected JsonObject createJsonObjectFromModel(UserDBModel model) {
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
