package use_cases_and_adapters.join_room;
import use_cases_and_adapters.RoomDBModel;

import entities.Room;
import entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * An interactor for join room use case.
 * This class implements JoinRoomInputBoundary to interact with user's input from outer layer.
 */
public class JoinRoomInteractor implements JoinRoomInputBoundary {
    private final JoinRoomDBGateway roomDBGateway;
    private final JoinRoomOutputBoundary presenter;

    public JoinRoomInteractor(JoinRoomDBGateway roomDBGateway, JoinRoomOutputBoundary presenter) {
        this.roomDBGateway = roomDBGateway;
        this.presenter = presenter;
    }

    /**
     * Handles join room use case using given room name from user.
     * If matching room is not found, it prepares a fail view showing error message.
     * If matching room is found, it prepares RoomView and user enters that room.
     *
     * @param roomName the name of room that user tries to join in
     */
    @Override
    public void handleJoinRoom(String roomName) {
        List<RoomDBModel> existingRooms = roomDBGateway.getRooms();
        for (RoomDBModel room : existingRooms) {
            if (room.getRoomName().equals(roomName)) {
                List<Integer> activeUsers = room.getActiveUserIDs();
                activeUsers.add(User.getUserID());
                room.setActiveUserIDs(activeUsers);
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
     * Returns a list of names of existing rooms.
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
