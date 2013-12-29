/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghostchat.database;

import com.ghostchat.factory.ChatFactory;
import com.ghostchat.model.Chat;
import com.ghostchat.model.GroupChat;
import com.ghostchat.model.Location;
import com.ghostchat.model.MessageBox;
import com.ghostchat.model.OneOnOneChat;
import com.ghostchat.model.User;
import java.util.LinkedList;
import java.util.List;

public class ChatStore {
 
    private List<Chat> chats;
    
    public ChatStore() {
        chats = new LinkedList<Chat>();
    }

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }

    public void addChat(Chat chat) {
        chats.add(chat);
    }
    
    public void removeChat(Chat chat) {
        chats.remove(chat);
    }
    
    public void createGroupChat(String chatname, boolean listed, String ownername, String ownerCity, String ownerCountry, boolean showOwnerlocation) {
        if(getChatByName(chatname) == null) {
            User owner = null;
            if(showOwnerlocation) {
                Location location = new Location(ownerCity, ownerCountry);
                owner = new User(ownername, location);
            } else {
                owner = new User(ownername);
            }
            Chat chat = new GroupChat(chatname, listed, owner);
            addChat(chat);
        }
    }
    
    public void createOneOnOneChat(String username1, String user1City, String user1Country, boolean user1Showlocation, String username2, String user2City, String user2Country, boolean user2Showlocation) {
        Chat chat = new OneOnOneChat();
        chat.addUser(username1, user1City, user1Country, user1Showlocation);
        chat.addUser(username2, user2City, user2Country, user2Showlocation);
        addChat(chat);
    }
    
    public Chat getChatByName(String chatname) {
        for(Chat c : chats) {
            if(c.getName().equalsIgnoreCase(chatname))
                return c;
        }
        return null;
    }
    
    public boolean chatExists(String chatname) {
        return getChatByName(chatname) != null;
    }
    
    public void addUserToChat(String chatname, String username, String city, String country, boolean showlocation) {
        Chat chat = getChatByName(chatname);
        if(chat != null) {
            chat.addUser(username, city, country, showlocation);
        }
    }
    
    public void removeUserFromChat(String username) {
        for(Chat chat : chats) {
            if(chat.userInChat(username)) {
                chat.removeUser(username);
                if(chat.countUsers() == 0) {
                    removeChat(chat);
                }
                break;
            } 
        }
    }
    
    public boolean isUserInChat(String chatname, String username) {
        Chat chat = getChatByName(chatname);
        if(chat != null) {
            return chat.userInChat(username);
        }
        return false;
    }
    
    public List<User> getUsers(String chatname) {
        Chat chat = getChatByName(chatname);
        if(chat != null) {
            return chat.getUsers();
        }
        return new LinkedList<User>();
    }
    
    public void sendMessage(String chatname, String username, String message) {
        if(chatExists(chatname)) {
            Chat chat = getChatByName(chatname);
            if(chat.userInChat(username)) {
                chat.sendMessage(username, message);
            }
        }
    }
    
    public List<MessageBox> getMessageBoxes(String chatname) {
        if(chatExists(chatname)) {
            Chat chat = getChatByName(chatname);
            return chat.getMessageBoxes();
        }
        return new LinkedList<MessageBox>();
    }
    
    public int countUsers() {
        int count = 0;
        for(Chat chat : chats) count += chat.countUsers();
        return count;
    }
    
    public int countChats() {
        return chats.size();
    }
    
    public User getChatOwner(String chatname) {
        Chat chat = getChatByName(chatname);
        return chat.getOwner();
    }
    
    public String generateChatName() {
        return ChatFactory.createUniqueId();
    }
    
    @Override
    public String toString() {
        return "ChatStore{" + "chats=" + chats + '}';
    }
    
}
