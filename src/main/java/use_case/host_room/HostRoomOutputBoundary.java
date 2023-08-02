package use_case.host_room;

public interface HostRoomOutputBoundary {
    void prepareRoomView(String messageHistory);
    void prepareMultipleHostingView();
}
