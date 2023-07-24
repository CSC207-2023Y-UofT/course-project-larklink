package host_room;

import javax.swing.JOptionPane;
public class HostRoomPresenter implements HostRoomOutputBoundary{
    @Override
    public void prepareRoomView(int userID) {
        JOptionPane.showMessageDialog(null, "Room Created by " + userID);
    }
    @Override
    public void prepareRoomNameAlreadyTaken() {
        JOptionPane.showMessageDialog(null, "Room name already taken");
    }
}
