package leave_room;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class RoomView {
    private String roomID;
    private String userID;
    private LeaveRoomController leaveRoomController;

    public RoomView(LeaveRoomController leaveRoomController, String roomID, String userID ){
        this.leaveRoomController = leaveRoomController;
        this.roomID = roomID;
        this.userID = userID;
    }
    public void prepareGUI(){
        JFrame frame  = new JFrame();
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
