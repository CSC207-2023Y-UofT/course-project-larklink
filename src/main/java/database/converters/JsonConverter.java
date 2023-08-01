package database.converters;

import com.google.gson.JsonObject;

/**
 * This interface defines methods for converting between JSON objects and instances of a specific class.
 *
 * @param <T> The type of the instances to convert to/from JSON.
 */
public interface JsonConverter<T> {

    /**
     * Converts a JsonObject into an instance of the specified type.
     *
     * @param jsonObject The JsonObject to convert.
     * @return An instance of the specified type with properties set according to the given JsonObject.
     */
    T toObject(JsonObject jsonObject);

    /**
     * Converts an instance of the specified type into a JsonObject.
     *
     * @param model The instance to convert.
     * @return A JsonObject representing the given instance.
     */
    JsonObject toJson(T model);
}
