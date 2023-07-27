package host_room;

import ui.Main;

import javax.swing.JOptionPane;
public class HostRoomPresenter implements HostRoomOutputBoundary {

    /**
     * Displays the RoomView passing in the specified room ID.
     * @param roomID the ID of the room.
     */
    @Override
    public void prepareRoomView(int roomID) {
        Main.roomID = roomID;
        Main.roomView.prepareGUI();
    }

    @Override
    public void prepareMultipleHostingView() {
        JOptionPane.showMessageDialog(null, "Already hosting room");
    }
}
