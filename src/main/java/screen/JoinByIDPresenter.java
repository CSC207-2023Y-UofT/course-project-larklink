package screen;

import join_room_use_case.JoinByIDOutputBoundary;

import javax.swing.*;

public class JoinByIDPresenter implements JoinByIDOutputBoundary {
    @Override
    public void prepareRoomView(){
    }
    @Override
    public void prepareFailView(String error){
        JOptionPane.showMessageDialog(null, error);
    }
}
