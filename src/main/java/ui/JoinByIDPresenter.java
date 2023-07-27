package ui;


import join_room.JoinByIDOutputBoundary;

import javax.swing.*;

public class JoinByIDPresenter implements JoinByIDOutputBoundary {
    @Override
    //modify later when RoomView is implemented
    public void prepareRoomView(Integer roomID){
        JOptionPane.showMessageDialog(null,
                "Join "+roomID);
    }
    @Override
    public void prepareFailView(){

        JOptionPane.showMessageDialog(null, "No Such Room Found!");
    }
}
