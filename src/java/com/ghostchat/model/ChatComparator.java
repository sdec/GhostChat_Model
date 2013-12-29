/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghostchat.model;

import java.util.Comparator;

public class ChatComparator implements Comparator<Chat>{

    @Override
    public int compare(Chat o1, Chat o2) {
       return o2.getUsers().size() - o1.getUsers().size(); // Grootste eerst
    }
    
}
