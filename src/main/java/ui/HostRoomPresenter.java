package ui;

import host_room.HostRoomOutputBoundary;

import javax.swing.JOptionPane;
public class HostRoomPresenter implements HostRoomOutputBoundary {
    @Override
    public void prepareRoomView(Integer userID) {
        JOptionPane.showMessageDialog(null,
                "Room Created by " + userID);
    }
    @Override
    public void prepareMultipleHostingView() {
        JOptionPane.showMessageDialog(null, "Already " +
                "hosting room");
    }
}
