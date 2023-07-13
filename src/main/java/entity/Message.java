package entity;

import java.time.LocalDate;
public class Message {
    private LocalDate timeStamp;
    private String content;
    private boolean edited;

    public Message(String content) {
        this.timeStamp = LocalDate.now();
        this.content = content;
        this.edited = false;
    }
}
