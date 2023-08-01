package database;

import com.google.gson.JsonObject;

public class UserConverter implements JsonConverter<UserDBModel> {

    @Override
    public UserDBModel toObject(JsonObject jsonObject) {
        int userId = jsonObject.get("id").getAsInt();
        String username = jsonObject.get("username").getAsString();
        String password = jsonObject.get("password").getAsString();

        return new UserDBModel(userId, username, password);
    }

    @Override
    public JsonObject toJson(UserDBModel model) {
        JsonObject userObject = new JsonObject();
        userObject.addProperty("username", model.getUsername());
        userObject.addProperty("password", model.getPassword());

        JsonObject mainObject = new JsonObject();
        mainObject.add("user", userObject);

        return mainObject;
    }
}
