package join_room;


import ui.View;
import ui.Viewable;

import javax.swing.*;

/**
 * A presenter for join room use case.
 * This class implements JoinRoomOutputBoundary to interact with JoinRoomInteractor.
 */
public class JoinRoomPresenter implements JoinRoomOutputBoundary {
    private Viewable view;

    /**
     * Displays RoomView of the room that user has entered.
     *
     * @param messageHistory a history of messages stored in the room
     */
    @Override
    public void prepareRoomView(String messageHistory){
        View.messageHistory = messageHistory;
        view.prepareGUI();
    }

    /**
     * Displays a fail view which indicates that matching room is not found.
     */
    @Override
    public void prepareFailView(){

        JOptionPane.showMessageDialog(null, "No Such Room Found!");
    }

    /**
     * Sets the view this presenter displays next
     *
     * @param view a view set for the next display
     */
    public void setView(View view) {
        this.view = view;
    }
}
