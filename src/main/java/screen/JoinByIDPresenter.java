package screen;

import join_room_use_case.JoinByIDOutputBoundary;

import javax.swing.*;

public class JoinByIDPresenter implements JoinByIDOutputBoundary {
    @Override
    public void prepareRoomView(){
    }
    @Override
    public void prepareFailView(){
        JOptionPane.showMessageDialog(null, "No Such Room Found!");
    }
}
