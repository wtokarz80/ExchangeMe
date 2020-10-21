package com.exchangeme.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User user;

    @OneToMany
    private List<Item> reservedItems;

    public Cart(){};

    public Cart(User user, List<Item> reservedItems) {
        this.user = user;
        this.reservedItems = reservedItems;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Item> getReservedItems() {
        return reservedItems;
    }

    public void setReservedItems(List<Item> reservedItems) {
        this.reservedItems = reservedItems;
    }
}
