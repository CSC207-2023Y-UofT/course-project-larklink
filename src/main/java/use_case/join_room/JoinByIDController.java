package use_case.join_room;

public class JoinByIDController {

    private final JoinByIDInputBoundary joinByIDInputBoundary;

    public JoinByIDController(JoinByIDInputBoundary joinByIDInputBoundary){
        this.joinByIDInputBoundary = joinByIDInputBoundary;
    }
    public void formatAndHandleJoinByID(String roomName, int userID){
        JoinByIDRequestModel requestModel = new JoinByIDRequestModel(roomName, userID);
        joinByIDInputBoundary.handleJoinByID(requestModel);
    }
}
