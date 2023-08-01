package use_cases_and_adapters.host_room;

public interface HostRoomOutputBoundary {
    void prepareRoomView(String messageHistory);
    void prepareMultipleHostingView();
}
