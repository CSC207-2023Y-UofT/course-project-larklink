package use_cases_and_adapters.join_room;

import use_cases_and_adapters.RoomDBModel;
import entities.Room;
import entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * An interactor for join room use case.
 * This class implements JoinRoomInputBoundary to interact with input from outer layer.
 */
public class JoinRoomInteractor implements JoinRoomInputBoundary {
    private final JoinRoomDBGateway roomDBGateway;
    private final JoinRoomOutputBoundary presenter;

    /**
     * Constructs a JoinRoomInteractor object with given gateway and output boundary object.
     *
     * @param roomDBGateway a JoinRoomDBGateway object
     * @param presenter a JoinRoomOutputBoundary object
     */
    public JoinRoomInteractor(JoinRoomDBGateway roomDBGateway, JoinRoomOutputBoundary presenter) {
        this.roomDBGateway = roomDBGateway;
        this.presenter = presenter;
    }

    /**
     * Handles join room use case using given room name from outer layer.
     * If matching room is not found, it prepares a fail view indicating that matching room is not found.
     * If matching room is found, it prepares RoomView and user enters that room.
     *
     * @param roomName the name of room that user tries to join in
     */
    @Override
    public void handleJoinRoom(String roomName) {
        List<RoomDBModel> existingRooms = roomDBGateway.getRooms();
        for (RoomDBModel room : existingRooms) {
            if (room.getRoomName().equals(roomName)) {
                // add this user into the active user list of this room and update the active user list
                List<Integer> activeUsers = room.getActiveUserIDs();
                activeUsers.add(User.getUserID());
                room.setActiveUserIDs(activeUsers);
                // update this room with new active user list
                roomDBGateway.updateARoom(room);
                Room.setRoom(room.getRoomID(), room.getRoomName(),
                        room.getHostID(), room.getActiveUserIDs(), room.getMessageHistory());
                presenter.prepareRoomView(Room.getMessageHistory());
                return; // return here so that prepareFailView is not called in this case
            }
        }
        // prepareFailView is only called if no matching room is found
        presenter.prepareFailView();
    }

    /**
     * Loads all names of the existing rooms.
     *
     * @return a list of all room names
     */
    @Override
    public List<String> loadRoomNames() {
        List<RoomDBModel> existingRooms = roomDBGateway.getRooms();
        List<String> roomNames = new ArrayList<>();
        for (RoomDBModel room : existingRooms) {
            roomNames.add(room.getRoomName());
        }
        return roomNames;
    }
}
