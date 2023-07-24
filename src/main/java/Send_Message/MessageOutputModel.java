package send_message;
import java.util.List;

public class MessageOutputModel {
    private final List<Entities.MessageEnt> msgList;

    public MessageOutputModel(List<Entities.MessageEnt> msgList) {
        this.msgList = msgList;
    }

    public List<Entities.MessageEnt> getMsgList(){
        return this.msgList;
    }
}
