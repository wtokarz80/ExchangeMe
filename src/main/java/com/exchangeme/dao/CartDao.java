package com.exchangeme.dao;

import com.exchangeme.models.Cart;

public class CartDao extends AbstractDao<Cart> {
    public CartDao() {
        super();
        this.aClass = Cart.class;
    }
}