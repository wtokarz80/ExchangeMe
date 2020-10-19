package com.exchangeme.models;

import java.util.List;

public class User {

    private long id;
    private String userName;
    private String email;
    private String password;

    private List<Item> items;

    public User(){

    }

    public User(String userName, String email, String password, List<Item> items) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.items = items;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
