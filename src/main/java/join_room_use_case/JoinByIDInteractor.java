package join_room_use_case;

import java.util.List;

public class JoinByIDInteractor implements JoinByIDInputBoundary{
    private RoomDBGateway roomDBGateway;
    private UserDBGateway userDBGateway;
    private JoinByIDOutputBoundary presenter;

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
            if (room.getRoom().getName().equals(requestModel.getRoomID())){
                List<UserModel> users = userDBGateway.loadUsers();
                for (UserModel user : users){
                    if (user.getUser().getUsername().equals(requestModel.getCurrUserID())){
                        roomDBGateway.updateRoomActiveUsers(user.getUser());
                        JoinByIDResponseModel responseModel = new JoinByIDResponseModel(true);
                        presenter.prepareRoomView(responseModel);
                    }
                } presenter.prepareFailView("Not a valid user");
            }
        }
        presenter.prepareFailView("No Such Room Found!");
        }

    }

