package use_cases.host_room;

public interface HostRoomOutputBoundary {
    void prepareRoomView(String messageHistory);
    void prepareMultipleHostingView();
}
