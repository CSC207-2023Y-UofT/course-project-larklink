package Send_Message.Presenter;
import Send_Message.Entities.MessageEnt;
import java.util.List;
public class MessageOutputModel {
    private final List<MessageEnt> msgList;

    public MessageOutputModel(List<MessageEnt> msgList) {
        this.msgList = msgList;
    }

    public List<MessageEnt> getMsgList(){
        return this.msgList;
    }
}
