package com.exchangeme.helpers;

import com.exchangeme.models.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;

public class DBFiller {

    public void fillDb() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exchangeMe");
        EntityManager em = emf.createEntityManager();

        User ola = new User("Ola", "ola@ola.pl", "ola123");
        User ala = new User("Ala", "ala@ala.pl", "ala123");
        User ula = new User("Ula", "ula@ula.pl", "ula123");

        Item bicycle = new Item(ola, "rower", Category.SPORT, Location.KROWODRZA, Status.AVAILABLE, "rower mtb");
        Item book = new Item(ala, "książka", Category.ROZRYWKA, Location.GRZEGÓRZKI, Status.AVAILABLE, "Kubuś Puchatek");
        Item sweter = new Item(ula, "sweter", Category.MODA, Location.KROWODRZA, Status.AVAILABLE, "sweter męski rozmiar L");
        Item cd = new Item(ula, "Zenek", Category.ROZRYWKA, Location.STARE_MIASTO, Status.AVAILABLE, "Top hits");

        List<Item> reservedItemsOla = Arrays.asList(book, cd);

        Cart olaCart = new Cart(ola, reservedItemsOla);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(ola);
        em.persist(ala);
        em.persist(ula);
        em.persist(bicycle);
        em.persist(book);
        em.persist(sweter);
        em.persist(cd);
        em.persist(olaCart);
        transaction.commit();
        System.out.println("\n--- SAVED ---\n");

        em.clear();
        em.close();
        emf.close();


    }
}
