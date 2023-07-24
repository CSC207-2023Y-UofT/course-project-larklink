package send_message;
import java.time.LocalDateTime;
public class MessageDBModel {
    private String sender;
    private String content;
    private LocalDateTime timestamp;

    public MessageDBModel(String sender, String content, LocalDateTime timestamp){
        this.sender = sender;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getSender() {
        return sender;
    }
    public String getContent(){
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
