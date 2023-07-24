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
    private HostRoomOutputBoundary outputBoundary;
    private HostRoomPresenter presenter;

    public HostRoomInteractor(RoomDBGateway database,
                              HostRoomOutputBoundary outputBoundary, HostRoomPresenter presenter) {
        this.database = database;
        this.outputBoundary = outputBoundary;
        this.presenter = presenter;
    }

    public void hostRoom(RoomModel request) {
        List<RoomDBModel> existingRooms = database.loadRooms();

        for (RoomDBModel existingRoom : existingRooms) {
            // user already hosting room
            if (existingRoom.getHost().equals(request.getHost())) {
                // NEED TO FIX
                presenter.prepareRoomNameAlreadyTaken();
                return; // found existing user, no need to check further
            }
        }

        RoomDBModel newRoom = new RoomDBModel(existingRooms.size() + 2, request.getActiveUsers(),
                request.getHost());
        database.

    }
}
