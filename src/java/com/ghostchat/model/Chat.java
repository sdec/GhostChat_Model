/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghostchat.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public abstract class Chat implements Comparable<Chat> {
    
    private String name;
    private boolean listed;
    private long started;
    private User owner;
    private User lastsender;
    
    private List<User> users;
    private Stack<Message> messages;

    public Chat(String name) {
        this(name, false, null);
    }
    
    public Chat(String name, boolean listed, User owner) {
        setName(name);
        setListed(listed);
        setStarted();
        setOwner(owner);
        
        this.users = new LinkedList<User>();    // LinkedList to quickly go through users
        this.messages = new Stack<Message>();      // Stack to make sure new messages get pushed
                                            // to the top of the list (used for construction of message boxes)
        
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isListed() {
        return listed;
    }

    public void setListed(boolean listed) {
        this.listed = listed;
    }

    public long getStarted() {
        return started;
    }

    // No params, the start time is set automatically to the current UNIX timestamp in milliseconds
    public void setStarted() {
        Date date = new Date();
        long currentMillis = date.getTime();
        this.started = currentMillis;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getLastsender() {
        return lastsender;
    }

    public void setLastsender(User lastsender) {
        this.lastsender = lastsender;
    }
    
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Stack<Message> getMessages() {
        return messages;
    }

    public void setMessages(Stack<Message> messages) {
        this.messages = messages;
    }

    public int countUsers() {
        return users.size();
    }
    
    public int countMessages() {
        return messages.size();
    }
    
    public User getUserByName(String username) {
        User user = new User(username);
        for(User u : users) {
            if(u.equals(user))
                return u;
        }
        return null;
    }
    
    public boolean userInChat(String username) {
        return getUserByName(username) != null;
    }
    
    public void addUser(User user) {
        users.add(user);
    }
    
    public void addUser(String username, String city, String country, boolean showlocation) {
        if(userInChat(username) == false) {
            User user;
            if(showlocation) {
                Location location = new Location(city, country);
                user = new User(username, location);
            } else {
                user = new User(username);
            }
            addUser(user);
        }
    }
    
    public void removeUser(User user) {
        users.remove(user);
    }
    
    public void removeUser(String username) {
        if(userInChat(username)) {
            removeUser(new User(username));
        }
    }
    
    public void addMessage(Message message) {
        messages.push(message);
    }
    
    public void sendMessage(String username, String message) {
        User sender = getUserByName(username);
        if(sender != null) {
            Message m = new Message(message, sender);
            addMessage(m);
            setLastsender(sender);
        }
    }
    
    public List<MessageBox> getMessageBoxes() {
        
        List<MessageBox> messageBoxes = new LinkedList<MessageBox>();
        MessageBox messageBox = new MessageBox();
        Stack<Message> messagesInBox = new Stack<Message>();
        User lastSender = null;
        Message lastMessage = messages.size() == 0 ? null : messages.get(messages.size() - 1);
        
        for(Message message : messages) {
            
            if(lastSender == null) {
                lastSender = message.getSender();
            }
            
            if(message.getSender().equals(lastSender) == false) {
                messageBox.messages = messagesInBox;
                messageBoxes.add(messageBox);
                messageBox = new MessageBox();
                messagesInBox = new Stack<Message>();
                lastSender = message.getSender();
            }
            
            messageBox.user = message.getSender();
            messageBox.sendtime = message.getSendTime();
            messageBox.location = message.getSender().hasLocation() ? message.getSender().getLocation() : null;
            
            messagesInBox.add(message);
            
            if(message.equals(lastMessage)) {
                messageBox.messages = messagesInBox;
                messageBoxes.add(messageBox);
            }
        }
        
        return messageBoxes;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + (this.listed ? 1 : 0);
        hash = 97 * hash + Objects.hashCode(this.owner);
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
        final Chat other = (Chat) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Chat{" + "name=" + name + ", listed=" + listed + ", starter=" + owner + ", users=" + users + ", messages=" + messages + '}';
    }
}
