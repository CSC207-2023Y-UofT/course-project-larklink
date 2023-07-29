package join_room;

import database.*;
import entities.Room;
import entities.User;
import models.*;


import java.util.List;

public class JoinByIDInteractor implements JoinByIDInputBoundary {
    private final RoomDBGateway roomDBGateway;
    private final JoinByIDOutputBoundary presenter;

    public JoinByIDInteractor(RoomDBGateway roomDBGateway, JoinByIDOutputBoundary presenter) {
        this.roomDBGateway = roomDBGateway;
        this.presenter = presenter;
    }

    @Override
    public void handleJoinByID(String roomName) {
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