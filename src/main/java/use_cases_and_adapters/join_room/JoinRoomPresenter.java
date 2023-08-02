package use_cases_and_adapters.join_room;


import views.View;

import javax.swing.*;

/**
 * A presenter for join room use case.
 * This class implements JoinRoomOutputBoundary to interact with JoinRoomInteractor.
 */
public class JoinRoomPresenter implements JoinRoomOutputBoundary {
    private View view;

    /**
     * Displays RoomView of the room that user has entered.
     *
     * @param messageHistory a history of messages stored in the room
     */
    // need to change javadoc after implementing view interface
    @Override
    public void prepareRoomView(String messageHistory){
        View.messageHistory = messageHistory;
        view.prepareGUI();
    }

    /**
     * Displays a fail view indicating that matching room is not found.
     */
    @Override
    public void prepareFailView(){

        JOptionPane.showMessageDialog(null, "No Such Room Found!");
    }

    /**
     * Sets the view this presenter displays next
     *
     * @param view a view set for the next display (in this case RoomView)
     */
    public void setView(View view) {
        this.view = view;
    }
}
