package ui;

import leave_room.LeaveRoomController;

import javax.swing.*;
import java.awt.*;

public class RoomView extends View {
    private final LeaveRoomController leaveRoomController;

    public RoomView(LeaveRoomController leaveRoomController){
        this.leaveRoomController = leaveRoomController;
    }

    @Override
    protected JPanel createPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton leaveButton = new JButton("Leave Room");
        leaveButton.addActionListener(e -> leaveRoomController.handleLeaveRoom(roomID, userID));

        panel.add(leaveButton);
        return panel;
    }
}
