package join_room_use_case;

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
        List<RoomDBResponseModel> rooms = roomDBGateway.loadRooms();

        for (RoomDBResponseModel room : rooms) {
            if (room.getRoom().getName().equals(requestModel.getRoomName())){
                List<UserModel> users = userDBGateway.loadUsers();
                for (UserModel user : users){
                    if (user.getUser().getUsername().equals(requestModel.getCurrUserID())){
                        roomDBGateway.updateRoomActiveUsers(user.getUser());
                        presenter.prepareRoomView(room.getRoom().getName());
                    }
                } presenter.prepareFailView("No Such User Found!");
            }
        }
        presenter.prepareFailView("No Such Room Found!");
        }

    }

