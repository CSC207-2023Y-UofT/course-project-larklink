package send_message;

public class SendMessageController {
    private final MessageInputBoundary inputBoundary;


    public SendMessageController(MessageInputBoundary inputBoundary){
        this.inputBoundary = inputBoundary;
    }
    /**
     * Formats the username and password into a SendMessageRequestModel and delegates the handling to SendMessageInteractor
     * through UserInputBoundary.
     * @param s the content entered by the user
     * @param sender the username of the sender
     * @param roomID the ID of the room
     */
    public void handleSendMessage(String s, String sender, String roomID){
        MessageInputModel request = new MessageInputModel(s, sender, roomID);
        inputBoundary.handleSendMessage(request);
    }
}