package leave_room;

import javax.swing.JOptionPane;

/**
 * After a user leaves a room, the view is presented by the LeaveRoomPresenter class.
 * It provides an implementation of the prepareHostOrJoinView method by implementing the LeaveRoomOutputBoundary interface.
 */
public class LeaveRoomPresenter implements LeaveRoomOutputBoundary {

    /**
     * prepares and displays the view using the LeaveRoomResponseModel that has been supplied.
     * A success message is shown if the response signifies a successful leave. If not, a failure notice is shown.
     *
     * @param responseModel The response model includes data on the operation's
     *                      success or failure in the leave room as well as any other information required for the view.
     */
    @Override
    public void prepareHostOrJoinView(LeaveRoomResponseModel responseModel) {
        if (responseModel.wasSuccess()) {
            JOptionPane.showMessageDialog(null, "You left the room");
        } else {
            JOptionPane.showMessageDialog(null, "Fail");
        }
    }
}
