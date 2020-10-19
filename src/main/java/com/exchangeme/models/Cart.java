package com.exchangeme.models;

import java.util.List;

public class Cart {

    private long id;
    private long userId;
    private List<Item> items;

    public Cart(){};

    public Cart(long userId, List<Item> items) {
        this.userId = userId;
        this.items = items;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
