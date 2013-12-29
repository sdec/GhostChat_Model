/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghostchat.facade;

import com.ghostchat.database.ChatStore;
import com.ghostchat.model.Chat;
import com.ghostchat.model.MessageBox;
import com.ghostchat.model.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;


@Stateless
@LocalBean
public class ChatFacade {

    private ChatStore store;
    
    public ChatFacade() {
        store = new ChatStore();
    }

    public void createGroupChat(String chatname, boolean listed, String ownername, String ownerCity, String ownerCountry, boolean showOwnerlocation) {
        store.createGroupChat(chatname, listed, ownername, ownerCity, ownerCountry, showOwnerlocation);
    }

    public void addUserToGroupChat(String chatname, String username, String city, String country, boolean showlocation) {
        store.addUserToChat(chatname, username, city, country, showlocation);
    }

    public void removeUserFromChat(String username) {
        store.removeUserFromChat(username);
    }

    public void createOneOnOneChat(String username1, String user1City, String user1Country, boolean user1Showlocation, String username2, String user2City, String user2Country, boolean user2Showlocation) {
        store.createOneOnOneChat(username1, user1City, user1Country, user1Showlocation, username2, user2City, user2Country, user2Showlocation);
    }

    public boolean isUserInChat(String chatname, String username) {
        return store.isUserInChat(chatname, username);
    }

    public boolean chatExists(String chatname) {
        return store.chatExists(chatname);
    }

    public void sendMessage(String chatname, String username, String message) {
        store.sendMessage(chatname, username, message);
    }

    public int countTotalUsers() {
        return store.countUsers();
    }

    public int countTotalChats() {
        return store.countChats();
    }

    public int countChatUsers(String chatname) {
        if (store.chatExists(chatname)) {
            return store.getChatByName(chatname).countUsers();
        }
        return 0;
    }

    public int countChatMessages(String chatname) {
        if (store.chatExists(chatname)) {
            return store.getChatByName(chatname).countMessages();
        }
        return 0;
    }

    public List<MessageBox> getMessageBoxes(String chatname) {
        return store.getMessageBoxes(chatname);
    }

    public List<Chat> getChats() {
        return store.getChats();
    }

    public List<User> getUsers(String chatname) {
        return store.getUsers(chatname);
    }
    
    public User getChatOwner(String chatname) {
        return store.getChatOwner(chatname);
    }
    
    public String generateChatName() {
        return store.generateChatName();
    }
}
