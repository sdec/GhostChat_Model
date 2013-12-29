/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghostchat.web;

import com.ghostchat.facade.ChatFacade;
import com.ghostchat.model.Chat;
import com.ghostchat.model.MessageBox;
import com.ghostchat.model.User;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Sander
 */
@WebService(serviceName = "NewWebService")
@Stateless()
public class NewWebService {
    @EJB
    private ChatFacade ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "createGroupChat")
    @Oneway
    public void createGroupChat(@WebParam(name = "chatname") String chatname, @WebParam(name = "listed") boolean listed, @WebParam(name = "ownername") String ownername, @WebParam(name = "ownerCity") String ownerCity, @WebParam(name = "ownerCountry") String ownerCountry, @WebParam(name = "showOwnerlocation") boolean showOwnerlocation) {
        ejbRef.createGroupChat(chatname, listed, ownername, ownerCity, ownerCountry, showOwnerlocation);
    }

    @WebMethod(operationName = "addUserToGroupChat")
    @Oneway
    public void addUserToGroupChat(@WebParam(name = "chatname") String chatname, @WebParam(name = "username") String username, @WebParam(name = "city") String city, @WebParam(name = "country") String country, @WebParam(name = "showlocation") boolean showlocation) {
        ejbRef.addUserToGroupChat(chatname, username, city, country, showlocation);
    }

    @WebMethod(operationName = "removeUserFromChat")
    @Oneway
    public void removeUserFromChat(@WebParam(name = "username") String username) {
        ejbRef.removeUserFromChat(username);
    }

    @WebMethod(operationName = "createOneOnOneChat")
    @Oneway
    public void createOneOnOneChat(@WebParam(name = "username1") String username1, @WebParam(name = "user1City") String user1City, @WebParam(name = "user1Country") String user1Country, @WebParam(name = "user1Showlocation") boolean user1Showlocation, @WebParam(name = "username2") String username2, @WebParam(name = "user2City") String user2City, @WebParam(name = "user2Country") String user2Country, @WebParam(name = "user2Showlocation") boolean user2Showlocation) {
        ejbRef.createOneOnOneChat(username1, user1City, user1Country, user1Showlocation, username2, user2City, user2Country, user2Showlocation);
    }

    @WebMethod(operationName = "isUserInChat")
    public boolean isUserInChat(@WebParam(name = "chatname") String chatname, @WebParam(name = "username") String username) {
        return ejbRef.isUserInChat(chatname, username);
    }

    @WebMethod(operationName = "chatExists")
    public boolean chatExists(@WebParam(name = "chatname") String chatname) {
        return ejbRef.chatExists(chatname);
    }

    @WebMethod(operationName = "sendMessage")
    @Oneway
    public void sendMessage(@WebParam(name = "chatname") String chatname, @WebParam(name = "username") String username, @WebParam(name = "message") String message) {
        ejbRef.sendMessage(chatname, username, message);
    }

    @WebMethod(operationName = "countTotalUsers")
    public int countTotalUsers() {
        return ejbRef.countTotalUsers();
    }

    @WebMethod(operationName = "countTotalChats")
    public int countTotalChats() {
        return ejbRef.countTotalChats();
    }

    @WebMethod(operationName = "countChatUsers")
    public int countChatUsers(@WebParam(name = "chatname") String chatname) {
        return ejbRef.countChatUsers(chatname);
    }

    @WebMethod(operationName = "countChatMessages")
    public int countChatMessages(@WebParam(name = "chatname") String chatname) {
        return ejbRef.countChatMessages(chatname);
    }

    @WebMethod(operationName = "getMessageBoxes")
    public List<MessageBox> getMessageBoxes(@WebParam(name = "chatname") String chatname) {
        return ejbRef.getMessageBoxes(chatname);
    }

    @WebMethod(operationName = "getChats")
    public List<Chat> getChats() {
        return ejbRef.getChats();
    }

    @WebMethod(operationName = "getUsers")
    public List<User> getUsers(@WebParam(name = "chatname") String chatname) {
        return ejbRef.getUsers(chatname);
    }

    @WebMethod(operationName = "getChatOwner")
    public User getChatOwner(@WebParam(name = "chatname") String chatname) {
        return ejbRef.getChatOwner(chatname);
    }

    @WebMethod(operationName = "generateChatName")
    public String generateChatName() {
        return ejbRef.generateChatName();
    }
    
}
