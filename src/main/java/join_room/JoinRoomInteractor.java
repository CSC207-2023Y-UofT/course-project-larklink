package join_room;

import entities.Room;
import entities.User;
import database.RoomDBModel;

import java.util.List;

/**
 * An interactor for join room use case.
 * This class implements JoinRoomInputBoundary to interact with user inputs from outer layer.
 */
public class JoinRoomInteractor implements JoinRoomInputBoundary {
    private final JoinRoomDBGateway roomDBGateway;
    private final JoinRoomOutputBoundary presenter;

    public JoinRoomInteractor(JoinRoomDBGateway roomDBGateway, JoinRoomOutputBoundary presenter) {
        this.roomDBGateway = roomDBGateway;
        this.presenter = presenter;
    }

    /**
     * Handles join room use case using inputs from user which are encapsulated in requestModel.
     * If a room with the given room name does not exist in room DB, it prepares a fail view showing error message.
     * If a room with the given room name exists in room DB, it prepares RoomView and user is saved to that room.
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
                roomDBGateway.joinARoom(User.getUserID(), room);
                Room.setRoom(room.getRoomID(), room.getRoomName(),
                        room.getHostID(), room.getActiveUserIDs(), room.getMessageHistory());
                presenter.prepareRoomView(Room.getMessageHistory());
                return; // return here so that prepareFailView is not called in this case
            }
        }
        // prepareFailView is only called if no matching room is found
        presenter.prepareFailView();
    }
}