package com.exchangeme.dao;

import com.exchangeme.models.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import static com.exchangeme.helpers.EntityM.ENTITY_MANAGER_FACTORY;

public class ItemDao {

    EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
    EntityTransaction transaction = em.getTransaction();

    public List<Item> getAll() {
        String strQuery = "SELECT i FROM Item i WHERE i.id IS NOT NULL";
        TypedQuery<Item> tq = em.createQuery(strQuery, Item.class);
        List<Item> items = new ArrayList<>();
        try {
            items = tq.getResultList();
            return items;
        }
        catch(NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return items;
    }

    public Item getItemById(long id) {
        return em.find(Item.class, id);
    }

    public void addItem(long id, String name, Category category, Location location, Status status, String description) {
        User user = em.find(User.class, id);
        if (id == user.getId()) {
            Item item = new Item(user, name, category, location, status, description);
            transaction.begin();
            em.persist(item);
            transaction.commit();
        } else {
            System.out.println("No such user id in DB");
        }
    }

//
//    public Item getItem(long id) {
//        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
//        String query = "SELECT i FROM Item i WHERE i.id = :id";
//        TypedQuery<Item> tq = em.createQuery(query, Item.class);
//        tq.setParameter("id", id);
//        Item item = null;
//        try {
//            item = tq.getSingleResult();
//            return item;
//        }
//        catch(NoResultException ex) {
//            ex.printStackTrace();
//        }
//        finally {
//            em.close();
//        }
//        return item;
//    }
}
