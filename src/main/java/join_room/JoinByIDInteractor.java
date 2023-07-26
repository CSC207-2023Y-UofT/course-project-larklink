package join_room;

import database.UserDBGateway;
import models.UserDBModel;

import java.util.List;

public class JoinByIDInteractor implements JoinByIDInputBoundary{
    private final RoomDBGateway roomDBGateway;
    private final UserDBGateway userDBGateway;
    private final JoinByIDOutputBoundary presenter;

    public JoinByIDInteractor(RoomDBGateway roomDBGateway, JoinByIDOutputBoundary presenter,
                              UserDBGateway userDBGateway){
        this.roomDBGateway = roomDBGateway;
        this.userDBGateway = userDBGateway;
        this.presenter = presenter;
    }

    @Override
    public void handleJoinByID(JoinByIDRequestModel requestModel){
        List<RoomDBModel> rooms = roomDBGateway.loadRooms();

        for (RoomDBModel room : rooms) {
            if (room.getName().equals(requestModel.getRoomName())){
                List<UserDBModel> users = userDBGateway.retrieveEveryUser();
                for (UserDBModel user : users){
                    if (user.getUserID() == requestModel.getCurrUserID()){
                        roomDBGateway.updateRoomActiveUsers(user);
                        presenter.prepareRoomView(room.getRoomID());
                        break;
                    }
                }
            }
        }
        presenter.prepareFailView();
        }

    }

