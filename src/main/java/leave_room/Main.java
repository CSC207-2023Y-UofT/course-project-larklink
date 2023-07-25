package leave_room;

public class Main {
    public static void main(String[] args) {
        RoomDBGateway roomDBGateway = new RoomDBAccess();
        LeaveRoomPresenter leaveRoomPresenter = new LeaveRoomPresenter();
        LeaveRoomInteractor leaveRoomInteractor = new LeaveRoomInteractor(roomDBGateway, leaveRoomPresenter);
        LeaveRoomController leaveRoomController = new LeaveRoomController(leaveRoomInteractor);
        RoomView roomView = new RoomView(leaveRoomController, 123, 111);
        roomView.prepareGUI();
    }
}
