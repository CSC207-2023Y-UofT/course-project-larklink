package leaveRoom;

public class Main {
    public static void main(String[] args) {
        RoomDBGateway roomDBGateway = new RoomDBAccess();
        LeaveRoomPresenter leaveRoomPresenter = new LeaveRoomPresenter();
        LeaveRoomInteractor leaveRoomInteractor = new LeaveRoomInteractor(roomDBGateway, leaveRoomPresenter);
        LeaveRoomController leaveRoomController = new LeaveRoomController(leaveRoomInteractor);
        RoomView roomView = new RoomView(leaveRoomController, "room1", "user1");
        roomView.prepareGUI();
    }
}
