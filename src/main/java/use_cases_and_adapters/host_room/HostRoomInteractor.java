package use_cases_and_adapters.host_room;

import entities.Room;
import entities.User;
import use_cases_and_adapters.RoomDBModel;

import java.util.ArrayList;
import java.util.List;


/**
 * An interactor for host room use case.
 * This class implements HostRoomInputBoundary to interact with input from outer layer.
 */
public class HostRoomInteractor implements HostRoomInputBoundary{
    private final HostRoomDBGateway database;
    private final HostRoomOutputBoundary presenter;

    public HostRoomInteractor(HostRoomDBGateway database, HostRoomOutputBoundary presenter) {
        this.database = database;
        this.presenter = presenter;
    }

    /**
     * Creates a new room and stores it in the database.
     * If a room is found with the same host, then the room is not created and a duplicateHostView is prepared.
     * If a room is found with the same name,
     * @param roomName the name of the room
     */
    public void hostRoom(String roomName) {
        List<RoomDBModel> existingRooms = database.getRooms();
        Integer hostID = User.getUserID();

        // check no duplicate room name
        for (RoomDBModel existingRoom: existingRooms) {
            if (existingRoom.getRoomName().equals(roomName)) {
                // duplicateNameView if two rooms have same name
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
        // construct the new room
        List<Integer> activeUsers = new ArrayList<>();
        activeUsers.add(hostID);
        Room.setRoom((existingRooms.size() + 2), roomName, hostID, activeUsers, "");

        RoomDBModel newRoom = new RoomDBModel(
                Room.getRoomID(), Room.getRoomName(), Room.getHostID(),
                hostID.toString(), // only one user in this new room
                Room.getMessageHistory());
        // add it to the db
        database.updateARoom(newRoom);
        // display the new room
        presenter.prepareRoomView(Room.getMessageHistory());
    }
}
