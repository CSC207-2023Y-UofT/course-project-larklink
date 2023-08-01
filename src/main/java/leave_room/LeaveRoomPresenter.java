package leave_room;

import ui.View;

import javax.swing.JOptionPane;

/**
 * The LeaveRoomPresenter class handles the view presentation after a user leaves a room.
 * It provides an implementation of the prepareJoinOrHostView and prepareFailedToLeaveRoomView methods
 * by implementing the LeaveRoomOutputBoundary interface.
 */
public class LeaveRoomPresenter implements LeaveRoomOutputBoundary {

    private View view;

    /**
     * Prepares and displays the view for a successful leave by showing the Join or Host view.
     * It calls the prepareGUI() method of the associated View to prepare and display the appropriate GUI.
     */
    @Override
    public void prepareJoinOrHostView() {
        view.prepareGUI();
    }

    /**
     * Prepares and displays the view for a failed leave by showing a failure notice using a dialog box.
     * It uses JOptionPane to display a message indicating that leaving the room has failed.
     */
    @Override
    public void prepareFailedToLeaveRoomView() {
        JOptionPane.showMessageDialog(null, "Failed to leave the room");
    }

    /**
     * Sets the associated View for this presenter.
     * This method allows setting the View that the presenter will interact with to present the output.
     *
     * @param view The View object to be associated with this presenter.
     */
    public void setView(View view) {
        this.view = view;
    }
}

