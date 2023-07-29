package join_room;


import ui.View;

import javax.swing.*;

public class JoinByIDPresenter implements JoinByIDOutputBoundary {
    private View view;
    @Override
    //modify later when RoomView is implemented
    public void prepareRoomView(String messageHistory){
        View.messageHistory = messageHistory;
        view.prepareGUI();
    }
    @Override
    public void prepareFailView(){

        JOptionPane.showMessageDialog(null, "No Such Room Found!");
    }
    public void setView(View view) {
        this.view = view;
    }
}
