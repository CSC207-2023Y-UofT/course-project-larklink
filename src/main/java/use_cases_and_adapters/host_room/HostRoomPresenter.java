package use_cases_and_adapters.host_room;

import use_cases_and_adapters.Viewable;

public class HostRoomPresenter implements HostRoomOutputBoundary {
    private Viewable view;
    /**
     * Displays the RoomView passing in the specified room ID.
     * @param messageHistory the messageHistory of the room.
     */
    @Override
    public void prepareRoomView(String messageHistory) {
        view.prepareGUI(messageHistory);
    }

    /**
     * Display a pop-up message indicating that the user is already
     * hosting another room with the name *hosting_room*
     * @param hostingRoom the room the host is already tied to
     */
    @Override
    public void prepareMultipleHostingView(String hostingRoom) {
        view.displayPopUpMessage("You're already hosting a room: " + hostingRoom);
    }

    /**
     * Display a pop-up message indicating that there already
     * exists a room with the specified name
     */
    public void prepareDuplicateNameView() {
        view.displayPopUpMessage("A room with this name already exists.");
    }

    /**
     * A setter to update the current view
     * @param view the view to update with
     */
    public void setView(Viewable view) {
        this.view = view;
    }
}
