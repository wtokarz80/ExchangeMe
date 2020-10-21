package com.exchangeme.dao;

import com.exchangeme.models.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public List<User> getAll() {

        EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
                .createEntityManagerFactory("exchangeMe");

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT u FROM User u WHERE u.id IS NOT NULL";

        TypedQuery<User> tq = em.createQuery(strQuery, User.class);
        List<User> users = new ArrayList<>();
        try {
            users = tq.getResultList();
            return users;
        }
        catch(NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return users;
    }
}
