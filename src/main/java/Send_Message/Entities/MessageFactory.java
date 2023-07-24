package Send_Message.Entities;

import Send_Message.Control.MessageInputModel;
//The purpose is to abstract away the process of constructing 'MessageEnt' entities from 'MessageModel'
public class MessageFactory {
    public MessageEnt create(MessageInputModel msgModel) {
        return new MessageEnt(msgModel.getContent(), msgModel.getSender(), msgModel.getRoomId());
    }
}
