package host_room;

import database.RoomDBGateway;
import entities.User;
import models.RoomDBModel;

import java.util.ArrayList;
import java.util.List;

public class HostRoomInteractor implements HostRoomInputBoundary{
    private final RoomDBGateway database;
    private final HostRoomOutputBoundary presenter;

    public HostRoomInteractor(RoomDBGateway database, HostRoomOutputBoundary presenter) {
        this.database = database;
        this.presenter = presenter;
    }

    /**
     * Creates a new room and stores it in the database.
     * If a room is found with the same host, then the room is not created and a duplicateHostView is prepared
     *
     */

    public void hostRoom(String roomName) {
        List<RoomDBModel> existingRooms = database.getRooms();
        Integer hostID = User.getUserID();

        for (RoomDBModel existingRoom : existingRooms) {
            if (existingRoom.getHost().equals(hostID)) {
                presenter.prepareMultipleHostingView(); // user already hosting room
                return;
            }
        }
        List<Integer> activeUsers = new ArrayList<>();
        activeUsers.add(hostID);
        System.out.println(activeUsers);
        RoomDBModel newRoom = new RoomDBModel(existingRooms.size() + 2, activeUsers,
                hostID, roomName, "");
        database.addARoom(newRoom);
        presenter.prepareRoomView(newRoom.getRoomID());
    }
}
