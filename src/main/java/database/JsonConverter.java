package database;

import com.google.gson.JsonObject;

public interface JsonConverter<T> {
    T toObject(JsonObject jsonObject);
    JsonObject toJson(T model);
}
