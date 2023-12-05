package com.example.sudokulogin;
package application;

import java.io.File;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.TreeSet;

public class User implements Comparable<User>, Serializable {

    String username;
    String password;
    String email;

    public User(String user, String passwd, String mail) {

        username = user;
        password = passwd;
        email = mail;

    }

    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (!(obj instanceof User))
            return false;

        User user = (User)obj;
        return username.equals(user.username) && password.equals(user.password);
    }

    public String getUserInfo() {
        return (username+" "+password+" "+email);
    }

    public String getUsername() {
        return (username);
    }

    public String getPassword() {
        return (password);
    }

    @Override
    public int compareTo(User o) {
        String u1 =this.username;
        String u2 = o.getUsername();
        if (u1.equals(u2)) {
            return 0;
        }

        if (u1.compareTo(u2)>1)
            return 1;