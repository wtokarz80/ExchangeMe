package com.exchangeme.dao;

import com.exchangeme.models.Item;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import static com.exchangeme.helpers.EntityM.ENTITY_MANAGER_FACTORY;

public class ItemDao {

    public List<Item> getAll() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
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
}
