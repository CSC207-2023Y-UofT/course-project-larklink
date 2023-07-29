package join_room;

public class JoinByIDController {

    private final JoinByIDInputBoundary joinByIDInputBoundary;

    public JoinByIDController(JoinByIDInputBoundary joinByIDInputBoundary){
        this.joinByIDInputBoundary = joinByIDInputBoundary;
    }
    public void formatAndHandleJoinByID(String roomName){
        JoinByIDRequestModel requestModel = new JoinByIDRequestModel(roomName);
        joinByIDInputBoundary.handleJoinByID(requestModel);
    }
}
