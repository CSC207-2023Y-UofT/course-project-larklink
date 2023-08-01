package database.converters;

import com.google.gson.JsonObject;
import database.UserDBModel;

/**
 * This class is responsible for converting UserDBModel instances to and from JSON.
 */
public class UserConverter implements JsonConverter<UserDBModel> {

    /**
     * Converts a JsonObject into a UserDBModel instance.
     *
     * @param jsonObject The JsonObject to convert. This should include properties for "id", "username", and "password".
     * @return A UserDBModel instance with properties set according to the given JsonObject.
     */
    @Override
    public UserDBModel toObject(JsonObject jsonObject) {
        int userId = jsonObject.get("id").getAsInt();
        String username = jsonObject.get("username").getAsString();
        String password = jsonObject.get("password").getAsString();

        return new UserDBModel(userId, username, password);
    }

    /**
     * Converts a UserDBModel instance into a JsonObject.
     *
     * @param model The UserDBModel instance to convert. This should include properties for "username" and "password".
     * @return A JsonObject representing the given UserDBModel instance.
     */
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
