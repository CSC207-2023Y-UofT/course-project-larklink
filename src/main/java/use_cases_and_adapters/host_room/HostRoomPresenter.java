package use_cases_and_adapters.host_room;

import views.View;
import use_cases_and_adapters.Viewable;

import javax.swing.JOptionPane;
public class HostRoomPresenter implements HostRoomOutputBoundary {
    private Viewable view;
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

    public void setView(Viewable view) {
        this.view = view;
    }
}
