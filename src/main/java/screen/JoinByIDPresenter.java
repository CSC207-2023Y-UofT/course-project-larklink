package screen;

import join_room_use_case.JoinByIDOutputBoundary;

import javax.swing.*;

public class JoinByIDPresenter implements JoinByIDOutputBoundary {
    @Override
    //modify later when RoomView is implemented
    public void prepareRoomView(String roomName){
        JOptionPane.showMessageDialog(null,
                "Join "+roomName);
    }
    @Override
    public void prepareFailView(String error){

        JOptionPane.showMessageDialog(null, error);
    }
}
