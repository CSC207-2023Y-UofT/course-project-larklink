package leave_room;

import database.DBAccess;
import database.HttpClient;
import database.RoomConverter;
import database.RoomDBModel;

import java.io.IOException;

public class LeaveRoomDBAccess extends DBAccess<RoomDBModel> implements LeaveRoomDBGateway {
    public LeaveRoomDBAccess(HttpClient httpClient, RoomConverter converter) {
        super(httpClient, converter);
    }

    @Override
    public RoomDBModel getARoom(Integer roomID) {
        try {
            return getARow(roomID);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void leaveARoom(RoomDBModel request) {
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