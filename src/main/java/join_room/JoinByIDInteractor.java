package join_room;

import database.*;
import models.*;


import java.util.List;

public class JoinByIDInteractor implements JoinByIDInputBoundary{
    private final RoomDBGateway roomDBGateway;
    private final JoinByIDOutputBoundary presenter;

    public JoinByIDInteractor(RoomDBGateway roomDBGateway, JoinByIDOutputBoundary presenter){
        this.roomDBGateway = roomDBGateway;
        this.presenter = presenter;
    }

    @Override
    public void handleJoinByID(JoinByIDRequestModel requestModel){
        List<RoomDBModel> rooms = roomDBGateway.getRooms();
        for (RoomDBModel room : rooms) {
            if (room.getName().equals(requestModel.getName())){
                List<Integer> activeUsers = room.getActiveUsers();
                activeUsers.add(requestModel.getUserID());
                room.setActiveUsers(activeUsers);
                roomDBGateway.joinARoom(requestModel.getUserID(), room);
                presenter.prepareRoomView(room.getRoomID());
                break;
                    }
                }
        presenter.prepareFailView();
        }
    }
