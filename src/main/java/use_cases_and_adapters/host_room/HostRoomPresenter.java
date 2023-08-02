package use_cases_and_adapters.host_room;

import views.View;

import javax.swing.JOptionPane;
public class HostRoomPresenter implements HostRoomOutputBoundary {
    private View view;
    /**
     * Displays the RoomView passing in the specified room ID.
     * @param messageHistory the messageHistory of the room.
     */
    @Override
    public void prepareRoomView(String messageHistory) {
        View.messageHistory = messageHistory;
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
