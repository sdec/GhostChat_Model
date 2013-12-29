/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghostchat.ui;

import com.ghostchat.facade.ChatFacade;
import com.ghostchat.model.Chat;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;

/**
 *
 * @author Sander
 */
public class Main {

    public static void main(String[] args) {

        ChatFacade facade = new ChatFacade();
        facade.createGroupChat("Group chat", true, "Sander", "Overijse", "Belgium", true);


        int users = 1000;
        int messages = 1000;

        for (int rep = 0; rep < 10; rep++) {
            for (int i = 0; i < users; i++) {
                String username = getRandomString();
                String chatname = getRandomString();
                facade.addUserToGroupChat(chatname, username, "", "", false);
                for (int j = 0; j < messages; j++) {
                    facade.sendMessage(chatname, username, getRandomString());
                    facade.sendMessage(chatname, username, getRandomString());
                    facade.sendMessage(chatname, username, getRandomString());
                }
            }

            Date date1 = new Date();
            System.out.println("Started test @ " + date1.toString());
            for (Chat chat : facade.getChats()) {
                chat.getMessageBoxes();
            }
            Date date2 = new Date();
            Date diff = new Date(date2.getTime() - date1.getTime());

            System.out.println("Ended test @ " + date2.toString());
            System.out.println(users + " chats with " + users + " users and " + (users * messages) + " messages took " + diff.getTime() + "ms");
        }
    }

    public static String getRandomString() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }
}
