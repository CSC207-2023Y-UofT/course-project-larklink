package screen;

import join_room_use_case.JoinByIDInputBoundary;
import join_room_use_case.JoinByIDRequestModel;

public class JoinByIDController {

    private JoinByIDInputBoundary joinByIDInputBoundary;

    public JoinByIDController(JoinByIDInputBoundary joinByIDInputBoundary){
        this.joinByIDInputBoundary = joinByIDInputBoundary;
    }
    public void formatAndHandleJoinByID(String roomID, String currUserID){
        JoinByIDRequestModel requestModel = new JoinByIDRequestModel(roomID, currUserID);
        joinByIDInputBoundary.handleJoinByID(requestModel);
    }
}
