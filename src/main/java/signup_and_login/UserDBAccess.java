package signup_and_login;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class UserDBAccess implements UserDBGateway {

    private static final String API_URL = "https://api.sheety.co/78ad1edb28469578058ca4c58c3f478b/larklink/users";

    /**
     * Loads the users from the database.
     * @return a list of UserRequestModel objects representing the existing users
     */
    @Override
    public List<UserRequestModel> loadUsers() {
        try {
            String result = performHttpRequest("GET", null);

            // Parse JSON data
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
            JsonArray usersArray = jsonObject.get("users").getAsJsonArray();

            List<UserRequestModel> users = new ArrayList<>();
            for (JsonElement userElement : usersArray) {
                JsonObject userObject = userElement.getAsJsonObject();
                String username = userObject.get("username").getAsString();
                String password = userObject.get("password").getAsString();
                users.add(new UserRequestModel(username, password));
            }

            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Saves a new user to the database (effectively signing them up).
     * @param request the UserRequestModel object representing the new user
     */
    @Override
    public void saveNewUser(UserRequestModel request) {
        try {
            JsonObject userObject = new JsonObject();
            userObject.addProperty("username", request.getUsername());
            userObject.addProperty("password", request.getPassword());

            JsonObject mainObject = new JsonObject();
            mainObject.add("user", userObject);

            String jsonInputString = mainObject.toString();
            performHttpRequest("POST", jsonInputString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     Performs an HTTP request to the specified URL with the given method and JSON input.
     This method is used internally by the UserDBAccess class.
     @param method the HTTP method (e.g., GET, POST)
     @param jsonInputString the JSON input string for POST requests, null for GET requests
     @return the response from the HTTP request
     @throws Exception if an error occurs during the HTTP request
     **/
    private String performHttpRequest(String method, String jsonInputString) throws Exception {
        URL url = new URL(UserDBAccess.API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);

        if (method.equals("POST")) {
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();
        }
    }
}
