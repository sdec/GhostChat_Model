/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghostchat.model;

import java.util.Stack;

public class MessageBox {

    public User user;
    public long sendtime;
    public Location location;
    public Stack<Message> messages = new Stack<Message>();

    @Override
    public String toString() {
        return "MessageBox{" + "user=" + user + ", messages=" + messages + '}';
    }
}