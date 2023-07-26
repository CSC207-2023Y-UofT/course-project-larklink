package database;

import com.google.gson.JsonObject;
import models.UserDBModel;

import javax.swing.plaf.synth.SynthMenuBarUI;
import java.util.List;

public class UserDBAccess extends DBAccess<UserDBModel> implements UserDBGateway {
    public UserDBAccess(String urlBase) {
        super(urlBase);
    }

    @Override
    protected UserDBModel parseJsonToObject(JsonObject jsonObject) {
        Integer userId = jsonObject.get("id").getAsInt();
        System.out.println(userId);
        String username = jsonObject.get("username").getAsString();
        System.out.println(username);
        String password = jsonObject.get("password").getAsString();
        System.out.println(password);

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

}
