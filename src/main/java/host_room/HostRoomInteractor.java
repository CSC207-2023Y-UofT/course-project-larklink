package host_room;

import entities.User;
import leaveRoom.RoomDBGateway;
import leaveRoom.RoomDBModel;
import leaveRoom.RoomDBRequestModel;
import leaveRoom.RoomModel;
import signup_and_login.UserDBModel;

import java.util.ArrayList;
import java.util.List;

public class HostRoomInteractor implements HostRoomInputBoundary{
    private RoomDBGateway database;
    private HostRoomOutputBoundary presenter;

    public HostRoomInteractor(RoomDBGateway database, HostRoomOutputBoundary presenter) {
        this.database = database;
        this.presenter = presenter;
    }

    public void hostRoom(RoomModel request) {
        List<RoomDBModel> existingRooms = database.loadRooms();

        for (RoomDBModel existingRoom : existingRooms) {
            // user already hosting room
            if (existingRoom.getHost().equals(request.getHost())) {
                // NEED TO FIX
                presenter.prepareRoomNameAlreadyTaken();
                return;
            }
        }

        RoomDBModel newRoom = new RoomDBModel(existingRooms.size() + 2, request.getActiveUsers(),
                request.getHost());
        database.saveRoom(newRoom);
        presenter.prepareRoomView(newRoom.getHost());
    }
}
