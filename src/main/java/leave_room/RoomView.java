package leave_room;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The GUI for a room view is represented by the RoomView class.
 * The room can be interacted with by users, who can also exit the area by using the "Leave Room" button.
 */
public class RoomView {
    private Integer roomID;
    private Integer userID;
    private LeaveRoomController leaveRoomController;

    /**
     * Constructs a new RoomView with the given LeaveRoomController, roomID, and userID.
     *
     * @param leaveRoomController The controller responsible for handling leave room actions.
     * @param roomID              The unique id of the room.
     * @param userID              The unique idr of the user in the room.
     */
    public RoomView(LeaveRoomController leaveRoomController, Integer roomID, Integer userID) {
        this.leaveRoomController = leaveRoomController;
        this.roomID = roomID;
        this.userID = userID;
    }

    /**
     * Creates and presents the room view's graphical user interface (GUI).
     * The "Leave Room" button on the GUI enables users to exit the space.
     * The LeaveRoomController's handleLeaveRoom function is called when the button is clicked.
     */
    public void prepareGUI() {
        JFrame frame = new JFrame();
        JButton leaveButton = new JButton("Leave Room");
        leaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leaveRoomController.handleLeaveRoom(roomID, userID);
            }
        });
        frame.add(leaveButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
