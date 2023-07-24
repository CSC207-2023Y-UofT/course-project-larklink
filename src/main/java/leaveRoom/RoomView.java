package leaveRoom;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class RoomView {
    private int roomId;
    private Integer currUserId;
    private LeaveRoomController leaveRoomController;

    public RoomView(LeaveRoomController leaveRoomController, Integer roomId, Integer currUserId ){
        this.leaveRoomController = leaveRoomController;
        this.roomId = roomId;
        this.currUserId = currUserId;
    }
    public void prepareGUI(){
        JFrame frame  = new JFrame();
        JButton leaveButton = new JButton("Leave Room");
        leaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leaveRoomController.handleLeaveRoom(roomId, currUserId);
            }
        });
        frame.add(leaveButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
