package use_cases_and_adapters.host_room;

import entities.Room;
import entities.User;
import use_cases_and_adapters.RoomDBModel;

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

        // check no duplicate room name
        for (RoomDBModel existingRoom: existingRooms) {
            if (existingRoom.getRoomName().equals(roomName)) {
                presenter.prepareDuplicateNameView();
                return;
            }
        }

        for (RoomDBModel existingRoom : existingRooms) {
            if (existingRoom.getHostID().equals(hostID)) {
                // user already hosting room
                String hostingRoom = existingRoom.getRoomName();
                presenter.prepareMultipleHostingView(hostingRoom);
                return;
            }
        }
        List<Integer> activeUsers = new ArrayList<>();
        activeUsers.add(hostID);
        Room.setRoom((existingRooms.size() + 2), roomName, hostID, activeUsers, "");

        RoomDBModel newRoom = new RoomDBModel(
                Room.getRoomID(), Room.getRoomName(), Room.getHostID(),
                Room.getActiveUserIDs(), Room.getMessageHistory());

        database.updateARoom(newRoom);
        presenter.prepareRoomView(Room.getMessageHistory());
    }
}
