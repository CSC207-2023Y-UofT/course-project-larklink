package leave_room;

import javax.swing.JOptionPane;

/**
 * The LeaveRoomPresenter class handles the view presentation after a user leaves a room.
 * It provides an implementation of the prepareJoinOrHostView and prepareFailedToLeaveRoomView methods
 * by implementing the LeaveRoomOutputBoundary interface.
 */
public class LeaveRoomPresenter implements LeaveRoomOutputBoundary {

    /**
     * Prepares and displays the view for successful leave by showing a success message.
     */
    @Override
    public void prepareJoinOrHostView() {
        JOptionPane.showMessageDialog(null, "You joined or hosted a room");
    }

    /**
     * Prepares and displays the view for a failed leave by showing a failure notice.
     */
    @Override
    public void prepareFailedToLeaveRoomView() {
        JOptionPane.showMessageDialog(null, "Failed to leave the room");
    }
}
