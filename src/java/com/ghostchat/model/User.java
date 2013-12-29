/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghostchat.model;

import java.util.Date;
import java.util.Objects;

public class User {
    
    private String username;
    private long joined;
    private Location location;

    // No location = locations disabled in settings
    public User(String username) {
        this(username, null);
    }
    
    public User(String username, Location location) {
        setUsername(username);
        setLocation(location);
        setJoined();
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getJoined() {
        return joined;
    }

    // No params, the join time is set automatically to the current UNIX timestamp in milliseconds upon user creation
    public void setJoined() {
        Date date = new Date();
        long currentMillis = date.getTime();
        this.joined = currentMillis;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    
    public boolean hasLocation() {
        return getLocation() != null;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.username);
        hash = 23 * hash + (int) (this.joined ^ (this.joined >>> 32));
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
        final User other = (User) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        Date date = new Date();
        return "User{" + "username=" + username + ", joined=" + date + ", location=" + location + '}';
    }
 
}
