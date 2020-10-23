package com.exchangeme.dao;

import com.exchangeme.models.Item;

public class ItemDao extends AbstractDao<Item> {

    public ItemDao() {
        super();
        this.aClass = Item.class;
    }
}