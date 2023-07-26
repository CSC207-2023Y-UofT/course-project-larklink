package leave_room;

import java.util.List;

/**
 * The business logic for exiting a room is handled by the LeaveRoomInteractor class.
 * To offer the leaveRoom method implementation, it implements the LeaveRoomInputBoundary interface.
 */
public class LeaveRoomInteractor implements LeaveRoomInputBoundary {
    private RoomDBGateway roomDBGateway;
    private LeaveRoomOutputBoundary leaveRoomOutputBoundary;

    /**
     * Constructs a new LeaveRoomInteractor with the given dependencies.
     *
     * @param roomDBGateway          The gateway to access room data from the database.
     * @param leaveRoomOutputBoundary The output boundary to handle the response after leaving the room.
     */
    public LeaveRoomInteractor(RoomDBGateway roomDBGateway, LeaveRoomOutputBoundary leaveRoomOutputBoundary) {
        this.roomDBGateway = roomDBGateway;
        this.leaveRoomOutputBoundary = leaveRoomOutputBoundary;
    }

    /**
     * Leaves a room for the given user after completing the action.
     * The user is taken off the list of active users if they are currently in the room.
     * Notifies the right response to the LeaveRoomOutputBoundary.
     *
     * @param roomID The unique id of the room to leave.
     * @param userID The unique id of the user who wants to leave the room.
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
                    leaveRoomOutputBoundary.prepareHostOrJoinView(new LeaveRoomResponseModel(true, false));
                }
            }
        }

        leaveRoomOutputBoundary.prepareHostOrJoinView(new LeaveRoomResponseModel(false, true));
    }
}
