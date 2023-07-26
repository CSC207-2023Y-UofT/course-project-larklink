package leave_room;

import java.util.List;

/**
 * The business logic for leaving a room is handled by the LeaveRoomInteractor class.
 * It implements the LeaveRoomInputBoundary interface to provide the leaveRoom method implementation.
 */
public class LeaveRoomInteractor implements LeaveRoomInputBoundary {
    private RoomDBGateway roomDBGateway;
    private LeaveRoomOutputBoundary leaveRoomOutputBoundary;

    /**
     * Constructs a new LeaveRoomInteractor with the specified dependencies.
     *
     * @param roomDBGateway          The gateway to access room data from the database.
     * @param leaveRoomOutputBoundary The output boundary to handle the response after leaving the room.
     */
    public LeaveRoomInteractor(RoomDBGateway roomDBGateway, LeaveRoomOutputBoundary leaveRoomOutputBoundary) {
        this.roomDBGateway = roomDBGateway;
        this.leaveRoomOutputBoundary = leaveRoomOutputBoundary;
    }

    /**
     * Leaves a room for the given user.
     * The user is removed from the list of active users if they are currently in the room.
     * The appropriate response is then notified to the LeaveRoomOutputBoundary.
     *
     * @param roomID The unique ID of the room to leave.
     * @param userID The unique ID of the user who wants to leave the room.
     */
    @Override
    public void leaveRoom(Integer roomID, Integer userID) {
        List<RoomDBRequestModel> rooms = roomDBGateway.loadRooms();

        for (RoomDBRequestModel room : rooms) {
            if (room.getRoomId().equals(roomID)) {
                List<Integer> activeUsers = room.getActiveUsers();
                if (activeUsers.contains(userID)) {
                    activeUsers.remove(userID);
                    roomDBGateway.updateRoomActiveUsers(room);
                    leaveRoomOutputBoundary.prepareJoinOrHostView();
                    return;
                }
            }
        }
        // The user was not found in the room or an error occurred during the leave process.
        // Notify the LeaveRoomOutputBoundary about the failure to leave the room.
        leaveRoomOutputBoundary.prepareFailedToLeaveRoomView();
    }
}
