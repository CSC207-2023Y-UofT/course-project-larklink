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

    @Override
    public List<UserRequestModel> loadUsers() {
        List<UserRequestModel> users = new ArrayList<>();
        try {
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            reader.close();

            users = parseJsonAndLoadUsers(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    private List<UserRequestModel> parseJsonAndLoadUsers(String jsonData) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonData, JsonObject.class);
        JsonArray usersArray = jsonObject.get("users").getAsJsonArray();

        List<UserRequestModel> users = new ArrayList<>();
        for (JsonElement userElement : usersArray) {
            JsonObject userObject = userElement.getAsJsonObject();
            String username = userObject.get("username").getAsString();
            String password = userObject.get("password").getAsString();
            users.add(new UserRequestModel(username, password));
        }
        System.out.println(users);
        return users;
    }

    @Override
    public void saveNewUser(UserRequestModel request) {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Set the appropriate headers and properties
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);

            // Create a user object
            JsonObject userObject = new JsonObject();
            userObject.addProperty("username", request.getUsername());
            userObject.addProperty("password", request.getPassword());

            // Create the main object and add the user object to it
            JsonObject mainObject = new JsonObject();
            mainObject.add("user", userObject);

            // Convert the main object to a string
            String jsonInputString = mainObject.toString();

            // Get the output stream of the connection and write the JSON input string to it
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Read and print the response from the server
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine;

                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
