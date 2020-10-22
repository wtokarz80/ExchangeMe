package com.exchangeme.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

import static com.exchangeme.helpers.EntityM.ENTITY_MANAGER_FACTORY;

public abstract class AbstractDao<T> implements IDao<T> {

    protected final EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
    protected final EntityTransaction transaction = em.getTransaction();
    protected Class<T> aClass;

    @Override
    public List<T> getAll() {
        return em.createQuery("SELECT u FROM " + aClass.getSimpleName() + " u", aClass).getResultList();
    }

    @Override
    public Optional<T> getById(Long id) {
        return Optional.ofNullable(em.find(aClass, id));
    }

    @Override
    public void insert(T t) {
        transaction.begin();
        em.persist(t);
        transaction.commit();
    }

    @Override
    public void update(T t) {
        insert(t);

    }

    @Override
    public void delete(Long id) {
        Optional<T> optional = getById(id);
        optional.ifPresent(this::removeObject);

    }
    private void removeObject(T t) {
        transaction.begin();
        em.remove(t);
        transaction.commit();
    }
}


