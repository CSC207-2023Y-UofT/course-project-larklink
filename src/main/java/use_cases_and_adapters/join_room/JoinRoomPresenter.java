package use_cases_and_adapters.join_room;

import use_cases_and_adapters.Viewable;

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
        view.prepareGUI(messageHistory);
    }

    /**
     * Displays a fail view indicating that matching room is not found.
     */
    @Override
    public void prepareFailView(){
        view.displayPopUpMessage("No Such Room Found!");
    }

    /**
     * Sets the view this presenter displays next
     *
     * @param view a view set for the next display (in this case RoomView)
     */
    public void setView(Viewable view) {
        this.view = view;
    }
}
