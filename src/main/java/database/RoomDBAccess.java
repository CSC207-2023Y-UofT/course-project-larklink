package database;

import host_room.HostRoomDBGateway;
import join_room.JoinByIDDBGateway;
import leave_room.LeaveRoomDBGateway;
import messaging.MessageDBGateway;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoomDBAccess extends DBAccess<RoomDBModel> implements HostRoomDBGateway, JoinByIDDBGateway,
        LeaveRoomDBGateway, MessageDBGateway {
    public RoomDBAccess(HttpClient httpClient, RoomConverter converter) {
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
    public RoomDBModel getARoom(Integer roomID) {
        try {
            return getARow(roomID);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateARoom(RoomDBModel request) {
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