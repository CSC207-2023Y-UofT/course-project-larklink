package database;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * This abstract class provides methods for accessing our database, including fetching and updating rows.
 * It uses a HttpClient for performing HTTP requests and a JsonConverter for converting between JSON and database model objects.
 *
 * @param <T> The type of the database model objects.
 */
public abstract class DBAccess<T> {
    private final JsonConverter<T> converter;
    private final HttpClient httpClient;
    private final Gson gson;

    public DBAccess(HttpClient httpClient, JsonConverter<T> converter) {
        this.httpClient = httpClient;
        this.converter = converter;
        this.gson = new Gson();
    }

    /**
     * Fetches all rows from the database and returns them as a list of database model objects.
     *
     * @return A list of database model objects representing the fetched rows.
     * @throws IOException If an I/O error occurs while performing the HTTP request.
     */
    protected List<T> getRows() throws IOException {
        String response = httpClient.performGETRequest(getRoute(), null);
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
        JsonArray jsonArray = jsonObject.get(getRoute()).getAsJsonArray();
        List<T> result = new ArrayList<>();
        for (JsonElement element : jsonArray) {
            result.add(converter.toObject(element.getAsJsonObject()));
        }
        return result;
    }

    /**
     * Fetches a row with a specific ID from the database and returns it as a database model object.
     *
     * @param rowID The ID of the row to fetch.
     * @return A database model object representing the fetched row, or null if no row with the given ID exists.
     * @throws IOException If an I/O error occurs while performing the HTTP request.
     */
    protected T getARow(Integer rowID) throws IOException {
        String response = httpClient.performGETRequest(getRoute(), rowID);
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
        return converter.toObject(jsonObject);
    }

    /**
     * Updates a row with a specific ID in the database, using a given database model object.
     *
     * @param rowID The ID of the row to update.
     * @param model The database model object to use for the update.
     * @throws IOException If an I/O error occurs while performing the HTTP request.
     */
    protected void updateARow(Integer rowID, T model) throws IOException {
        String jsonInputString = converter.toJson(model).toString();
        httpClient.performPUTRequest(getRoute(), rowID, jsonInputString);
    }


    /**
     * Returns the route for the database table that this DBAccess instance interacts with.
     *
     * @return The route for the database table.
     */
    protected abstract String getRoute();
}
