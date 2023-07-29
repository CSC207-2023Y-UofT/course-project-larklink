package join_room;

import database.*;
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
    public void handleJoinByID(JoinByIDRequestModel requestModel) {
        List<RoomDBModel> rooms = roomDBGateway.getRooms();
        for (RoomDBModel room : rooms) {
        if (room.getName().equals(requestModel.getName())) {
                List<Integer> activeUsers = room.getActiveUsers();
                activeUsers.add(User.getUserID());
                room.setActiveUsers(activeUsers);
                roomDBGateway.joinARoom(User.getUserID(), room);
                presenter.prepareRoomView(room.getRoomID());
                return; // return here so that prepareFailView is not called in this case
            }
        }
        // prepareFailView is only called if no matching room is found
        presenter.prepareFailView();
    }
}