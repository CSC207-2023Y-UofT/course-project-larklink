package join_room_use_case;

public interface JoinByIDOutputBoundary {
    void prepareRoomView(JoinByIDResponseModel responseModel);
    void prepareFailView(String message);

}
