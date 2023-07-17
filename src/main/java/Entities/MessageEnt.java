package Entities;


import java.io.Serializable;
import java.time.LocalDateTime;

public class MessageEnt implements Serializable {
    /**
     * A content of the message
     */
    private String content;

    /**
     * A date and time when the message is sent
     */
    private LocalDateTime timeStamp;

    /**
     * An Account of the user who sent the message
     */
    private String sender;
    private String roomId;
    /**
     * Creates an instance of MessageEnt
     * @param content
     * @param sender
     */
    public MessageEnt(String content, String sender, String roomId){
        this.content = content;
        this.sender = sender;
        this.roomId = roomId;
        timeStamp = LocalDateTime.now();
    }

    public LocalDateTime getSentTime(){
        return timeStamp;
    }

    public String getSender(){
        return sender;
    }
    public String getRoomId(){
        return roomId;
    }

    public String getContent(){
        return content;
    }

    public String[] getInfo(){
        String[] info = {this.content, this.sender};
        return info;
    }
}