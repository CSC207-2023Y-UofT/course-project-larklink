package database;

import signup_and_login.UserDBGateway;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserDBAccess extends DBAccess<UserDBModel> implements UserDBGateway {

    public UserDBAccess(HttpClient httpClient, UserConverter converter) {
        super(httpClient,converter);
    }

    @Override
    public List<UserDBModel> getUsers() {
        try {
             return getRows();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void addAUser(UserDBModel request) {
        try {
            updateARow(request.getUserID(), request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getRoute() {
        return "users";
    }
}
