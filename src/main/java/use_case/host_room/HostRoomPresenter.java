package use_case.host_room;

import ui.View;

import javax.swing.JOptionPane;
public class HostRoomPresenter implements HostRoomOutputBoundary {
    private View view;
    /**
     * Displays the RoomView passing in the specified room ID.
     * @param roomID the ID of the room.
     */
    @Override
    public void prepareRoomView(int roomID) {
        View.roomID = roomID;
        view.prepareGUI();
    }

    @Override
    public void prepareMultipleHostingView() {
        JOptionPane.showMessageDialog(null, "Already hosting room");
    }

    public void setView(View view) {
        this.view = view;
    }
}
