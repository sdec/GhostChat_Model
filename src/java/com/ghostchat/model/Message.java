package com.ghostchat.model;

import java.util.Date;
import java.util.Objects;


public class Message {
    
    private String message;
    private long sendTime;
    private User sender; 
    
    // No sender = message from server
    public Message(String message) {
        this(message, null);
    }
    
    public Message(String message, User sender) {
        setMessage(message);
        setSendTime();
        setSender(sender);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getSendTime() {
        return sendTime;
    }

    // No params, the send time is set automatically to the current UNIX timestamp in milliseconds
    public void setSendTime() {
        Date date = new Date();
        long currentMillis = date.getTime();
        this.sendTime = currentMillis;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }
    
    public boolean hasSender() {
        return getSender() != null;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.message);
        hash = 97 * hash + (int) (this.sendTime ^ (this.sendTime >>> 32));
        hash = 97 * hash + Objects.hashCode(this.sender);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Message other = (Message) obj;
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        if (this.sendTime != other.sendTime) {
            return false;
        }
        if (!Objects.equals(this.sender, other.sender)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Message{" + "message=" + message + ", sendTime=" + sendTime + ", sender=" + sender + '}';
    }
}
