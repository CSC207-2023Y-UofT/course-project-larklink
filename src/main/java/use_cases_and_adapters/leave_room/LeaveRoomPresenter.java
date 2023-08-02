package use_cases_and_adapters.leave_room;

import views.View;

import javax.swing.JOptionPane;

/**
 * The LeaveRoomPresenter class handles the view presentation after a user leaves a room.
 * It provides an implementation of the prepareJoinOrHostView and prepareFailedToLeaveRoomView methods
 * by implementing the LeaveRoomOutputBoundary interface.
 */
public class LeaveRoomPresenter implements LeaveRoomOutputBoundary {
    private View view;

    /**
     * Prepares and displays the view for successful leave by showing a success message.
     */
    @Override
    public void prepareJoinOrHostView() {
        view.prepareGUI();
    }

    /**
     * Prepares and displays the view for a failed leave by showing a failure notice.
     */
    @Override
    public void prepareFailedToLeaveRoomView() {
        JOptionPane.showMessageDialog(null, "Failed to leave the room");
    }
    public void setView(View view) {
        this.view = view;
    }
}
