package use_cases_and_adapters.leave_room;

import entities.Room;
import entities.User;
import use_cases_and_adapters.RoomDBModel;

import java.util.List;

public class LeaveRoomInteractor implements LeaveRoomInputBoundary {
    private final LeaveRoomDBGateway roomDBGateway;
    private final LeaveRoomOutputBoundary leaveRoomOutputBoundary;

    public LeaveRoomInteractor(LeaveRoomDBGateway roomDBGateway, LeaveRoomOutputBoundary leaveRoomOutputBoundary) {
        this.roomDBGateway = roomDBGateway;
        this.leaveRoomOutputBoundary = leaveRoomOutputBoundary;
    }

    @Override
    public void leaveRoom() {
        RoomDBModel room = roomDBGateway.getARoom(Room.getRoomID());
        List<Integer> activeUsers = room.getActiveUserIDs();
        if (activeUsers.contains(User.getUserID())) {
            activeUsers.remove(User.getUserID());
            room.setActiveUserIDs(activeUsers);
            roomDBGateway.updateARoom(room);
            leaveRoomOutputBoundary.prepareJoinOrHostView();
            return;
        }
        leaveRoomOutputBoundary.prepareFailedToLeaveRoomView();
    }
}
