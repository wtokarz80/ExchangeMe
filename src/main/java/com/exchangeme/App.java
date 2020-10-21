package com.exchangeme;

import com.exchangeme.helpers.DBFiller;

public class App {

    public static void main(String[] args) {
        DBFiller dbFiller = new DBFiller();
        dbFiller.fillDb();
    }
}
