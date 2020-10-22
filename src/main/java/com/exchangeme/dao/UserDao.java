package com.exchangeme.dao;

import com.exchangeme.models.Item;
import com.exchangeme.models.User;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

import static com.exchangeme.helpers.EntityM.ENTITY_MANAGER_FACTORY;

public class UserDao implements IDao<User>{

    EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
    EntityTransaction transaction = em.getTransaction();

    @Override
    public List<User> getAll() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public Optional<User> getById(Long id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    @Override
    public void insert(User user) {
        transaction.begin();
        em.persist(user);
        transaction.commit();
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(Long id) {
        Optional<User> optionalUser = getById(id);
        optionalUser.ifPresent(this::removeObject);

    }

    private void removeObject(User user) {
        System.out.println(user);
        transaction.begin();
        em.remove(user);
        transaction.commit();
    }


}
