package com.exchangeme.dao;

import com.exchangeme.models.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.exchangeme.helpers.EntityM.ENTITY_MANAGER_FACTORY;

public class UserDao {

    public List<User> getAll() {
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

    public User getUser(long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT u FROM User u WHERE u.id = :id";
        TypedQuery<User> tq = em.createQuery(query, User.class);
        tq.setParameter("id", id);
        User user = null;
        try {
            user = tq.getSingleResult();
            return user;
        }
        catch(NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return user;
    }
}
