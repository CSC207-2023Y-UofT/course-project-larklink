package ui;

import leave_room.LeaveRoomOutputBoundary;
import leave_room.LeaveRoomResponseModel;

import javax.swing.JOptionPane;

public class LeaveRoomPresenter implements LeaveRoomOutputBoundary {
    @Override
    public void prepareHostOrJoinView(LeaveRoomResponseModel responseModel) {
        if (responseModel.wasSuccess()) {
            JOptionPane.showMessageDialog(null, "You left the room");
        }
        else {
            JOptionPane.showMessageDialog(null, "Fail" );
        }
    }
}
