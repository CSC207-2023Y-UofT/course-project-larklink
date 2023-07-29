package messaging;
import java.time.LocalDateTime;
public class MessageResponseModel {
    private String sender;
    private String content;
    private LocalDateTime timestamp;
    private Boolean isLark;
    private Boolean isDelivered;

    public MessageResponseModel(String sender, String content, LocalDateTime timestamp, Boolean isLark, Boolean isDelivered){
        this.sender = sender;
        this.content = content;
        this.timestamp = timestamp;
        this.isLark = isLark;
        this.isDelivered = isDelivered;
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

    public Boolean getLark() {
        return isLark;
    }

    public Boolean getDelivered() {
        return isDelivered;
    }
}
