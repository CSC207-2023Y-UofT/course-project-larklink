package leave_room;

import entities.Room;
import entities.User;
import database.RoomDBModel;

import java.util.List;

/**
 * The LeaveRoomInteractor class handles the logic for leaving a room and communicates with the database.
 * It implements the LeaveRoomInputBoundary interface to define the leaveRoom() method.
 */
public class LeaveRoomInteractor implements LeaveRoomInputBoundary {

    private final LeaveRoomDBGateway roomDBGateway;
    private final LeaveRoomOutputBoundary leaveRoomOutputBoundary;

    /**
     * Constructs a new LeaveRoomInteractor with the provided dependencies.
     *
     * @param roomDBGateway         The gateway interface for interacting with the Room database.
     * @param leaveRoomOutputBoundary The output boundary interface for preparing views based on the leave room operation.
     */
    public LeaveRoomInteractor(LeaveRoomDBGateway roomDBGateway, LeaveRoomOutputBoundary leaveRoomOutputBoundary) {
        this.roomDBGateway = roomDBGateway;
        this.leaveRoomOutputBoundary = leaveRoomOutputBoundary;
    }

    /**
     * the LeaveRoomInputBoundary interface's implementation of the leaveRoom() method.
     * If the current User is on the list of currently active users for the Room, it removes them from the list and updates the database.
     * Using the LeaveRoomOutputBoundary interface, it prepares the appropriate view following a successful removal.
     * If the User is not in the active user list, it prepares a view indicating a failed attempt to leave the room.
     */
    @Override
    public void leaveRoom() {
        RoomDBModel room = roomDBGateway.getARoom(Room.getRoomID());
        List<Integer> activeUsers = room.getActiveUserIDs();
        if (activeUsers.contains(User.getUserID())) {
            activeUsers.remove(User.getUserID());
            room.setActiveUserIDs(activeUsers);
            roomDBGateway.leaveARoom(room);
            leaveRoomOutputBoundary.prepareJoinOrHostView();
            return;
        }
        leaveRoomOutputBoundary.prepareFailedToLeaveRoomView();
    }
}

