/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghostchat.factory;

import com.ghostchat.model.Chat;
import com.ghostchat.model.OneOnOneChat;
import java.util.Random;

public class ChatFactory {
    
    static long extraid = 1;
    
    public static Chat createOneOnOneChat() {
        return new OneOnOneChat();
    }
    
    public static String createUniqueId() {
        char[] chars = "abcdefghijklmnopqrstuvwxyzABSDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        Random r = new Random(System.currentTimeMillis() + extraid);
        extraid++;
        char[] id = new char[8];
        for (int i = 0; i < 8; i++) {
            id[i] = chars[r.nextInt(chars.length)];
        }
        return new String(id);
    }
    
}
