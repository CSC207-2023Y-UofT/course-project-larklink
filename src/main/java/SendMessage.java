public class SendMessage {
    private String senderName;
    private String messageContent;
    private boolean isLark;

    public SendMessage(String senderName, String messageContent, boolean isLark) {
        this.senderName = senderName;
        this.messageContent = messageContent;
        this.isLark = isLark;
    }

    public void printMessage() {
        System.out.println("Sender: " + senderName);
        System.out.println("Message: " + messageContent);
        System.out.println("Is Lark: " + isLark);
    }
}
