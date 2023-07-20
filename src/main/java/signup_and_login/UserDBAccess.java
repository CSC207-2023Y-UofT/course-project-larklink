package signup_and_login;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDBAccess implements UserDBGateway {
    private Connection connection;

    public UserDBAccess() {
        // Initialize the database connection
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<UserRequestModel> loadUsers() {
        List<UserRequestModel> users = new ArrayList<>();

        // Fetch users from the database and populate the list
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");

                UserRequestModel user = new UserRequestModel(username, email);
                users.add(user);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public void saveNewUser(UserRequestModel request) {
        // Save the new user to the database
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
            statement.setString(1, request.getUsername());
            statement.setString(2, request.getPassword());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
