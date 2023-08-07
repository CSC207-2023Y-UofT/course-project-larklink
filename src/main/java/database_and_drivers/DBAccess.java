package database_and_drivers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import database_and_drivers.converters.JsonConverter;
import kong.unirest.Unirest;

import java.util.ArrayList;
import java.util.List;


/**
 * This abstract class provides methods for accessing our database, including fetching and updating rows.
 *
 * @param <T> The type of the database model objects.
 */
public abstract class DBAccess<T> {
    private final JsonConverter<T> converter;
    private final Gson gson;

    /**
     * Constructs a new RoomDBAccess object with the given HttpClient and UserConverter instances.
     *
     * @param converter  The UserConverter instance responsible for switching between JSON data to User objects.
     */
    public DBAccess(JsonConverter<T> converter) {
        this.converter = converter;
        this.gson = new Gson();
    }

    /**
     * Fetches all rows from the database and returns them as a list of database model objects.
     *
     * @return A list of database model objects representing the fetched rows.
     */
    protected List<T> getRows()  {
        List<T> result = new ArrayList<>();
        String response = Unirest.get(getRoute()).asString().getBody();
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
        JsonArray rows = jsonObject.get(getRoute()).getAsJsonArray();
        for (JsonElement row : rows) {
            result.add(converter.toObject(row.getAsJsonObject()));
        }
        return result;
    }

    /**
     * Fetches a row with a specific ID from the database and returns it as a database model object.
     *
     * @param rowID The ID of the row to fetch.
     * @return A database model object representing the fetched row, or null if no row with the given ID exists.
     */
    protected T getARow(Integer rowID) {
        String response = Unirest.get(getRoute() + "/" + rowID).asString().getBody();
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
        return converter.toObject(jsonObject);
    }

    /**
     * Updates a row with a specific ID in the database, using a given database model object.
     *
     * @param rowID The ID of the row to update.
     * @param model The database model object to use for the update.
     */
    protected void updateARow(Integer rowID, T model)  {
        String jsonString = converter.toJson(model).toString();
        Unirest.put(getRoute() + "/" + rowID)
                .header("Content-Type", "application/json")
                .body(jsonString)
                .asString();
    }

    /**
     * Returns the route for the database table that this DBAccess instance interacts with.
     *
     * @return The route for the database table.
     */
    protected abstract String getRoute();
}
