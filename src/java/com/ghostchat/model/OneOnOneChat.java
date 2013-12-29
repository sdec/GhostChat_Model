/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghostchat.model;

import com.ghostchat.factory.ChatFactory;

public class OneOnOneChat extends Chat {

    public OneOnOneChat() {
        super("1g56re4f65e4zf35er4gh", false, null);
        generateChatname();
    }

    private void generateChatname() {
        String generatedChatname = ChatFactory.createUniqueId();
        setName(generatedChatname);
    }
    
    @Override
    public void addUser(User user) {
        if(countUsers() < 2)
            getUsers().add(user);
    }
    
    @Override
    public int compareTo(Chat o) {
        return compareTo(o);
    }
}
