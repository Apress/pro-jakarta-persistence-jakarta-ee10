package examples.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Message {
    @Id
    private int id;
    private String message;
    @Temporal(TemporalType.DATE)
    private Date postingDate;
    @ManyToOne Conversation conversation;

    public int getId() {
        return id;
    }
    
    public void setId(int empNo) {
        this.id = empNo;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String name) {
        this.message = name;
    }

    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }
    
    public String toString() {
        return "Message " + getId() + ": " + getMessage() + 
               " posted: " + getPostingDate();
    }
}
