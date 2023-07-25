package join_room;

public class JoinByIDController {

    private final JoinByIDInputBoundary joinByIDInputBoundary;

    public JoinByIDController(JoinByIDInputBoundary joinByIDInputBoundary){
        this.joinByIDInputBoundary = joinByIDInputBoundary;
    }
    public void formatAndHandleJoinByID(String roomName, int currUserID){
        JoinByIDRequestModel requestModel = new JoinByIDRequestModel(roomName, currUserID);
        joinByIDInputBoundary.handleJoinByID(requestModel);
    }
}
