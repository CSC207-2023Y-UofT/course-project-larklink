package join_room;

import database.DBAccess;
import database.HttpClient;
import database.RoomConverter;
import database.RoomDBModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JoinRoomDBAccess extends DBAccess<RoomDBModel> implements JoinByIDDBGateway {
    public JoinRoomDBAccess(HttpClient httpClient, RoomConverter converter) {
        super(httpClient, converter);
    }

    @Override
    public List<RoomDBModel> getRooms() {
        try {
            return getRows();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void joinARoom(RoomDBModel request) {
        try {
            updateARow(request.getRoomID(), request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getRoute() {
        return "rooms";
    }
}