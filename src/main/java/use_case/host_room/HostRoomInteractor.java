package use_case.host_room;

import entities.Room;
import entities.User;
import database.RoomDBModel;

import java.util.ArrayList;
import java.util.List;

public class HostRoomInteractor implements HostRoomInputBoundary{
    private final HostRoomDBGateway database;
    private final HostRoomOutputBoundary presenter;

    public HostRoomInteractor(HostRoomDBGateway database, HostRoomOutputBoundary presenter) {
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
            if (existingRoom.getHostID().equals(hostID)) {
                presenter.prepareMultipleHostingView(); // user already hosting room
                return;
            }
        }
        List<Integer> activeUsers = new ArrayList<>();
        activeUsers.add(hostID);
        Room.setRoom((existingRooms.size() + 2), roomName, hostID, activeUsers, "");

        RoomDBModel newRoom = new RoomDBModel(
                Room.getRoomID(), Room.getRoomName(), Room.getHostID(),
                Room.getActiveUserIDs(), Room.getMessageHistory());

        database.addARoom(newRoom);
        presenter.prepareRoomView(Room.getMessageHistory());
    }
}
