/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghostchat.factory;

import com.ghostchat.database.ChatStore;

public class ChatStoreFactory {
    
    public static ChatStore createChatStore() {
        return new ChatStore();
    }
    
}
