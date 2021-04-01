package com.chat.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "messages")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private MessageType type;
    
    @Column(name = "message")
    private String content;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    private String sender;
    
    public ChatMessage(){}

    public ChatMessage(String content) {
        this.content = content;
    }

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content.trim();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }



    @Override
    public String toString() {
        return "ChatMessage{" + "id=" + id + ", sender=" + sender + '}';
    }




    
}
