package join_room;

public class JoinByIDController {

    private final JoinByIDInputBoundary joinByIDInputBoundary;

    public JoinByIDController(JoinByIDInputBoundary joinByIDInputBoundary){
        this.joinByIDInputBoundary = joinByIDInputBoundary;
    }
    public void formatAndHandleJoinByID(String name, Integer currUserID){
        JoinByIDRequestModel requestModel = new JoinByIDRequestModel(name, currUserID);
        joinByIDInputBoundary.handleJoinByID(requestModel);
    }
}
