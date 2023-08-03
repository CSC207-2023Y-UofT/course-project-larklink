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

    @Override
    public void prepareMultipleHostingView() {
        view.displayPopUpMessage("You're already hosting another room.");
    }

    public void prepareDuplicateNameView() {
        view.displayPopUpMessage("A room with this name already exists.");
    }

    public void setView(Viewable view) {
        this.view = view;
    }
}
