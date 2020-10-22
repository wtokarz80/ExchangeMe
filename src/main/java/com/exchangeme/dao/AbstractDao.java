package com.exchangeme.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

import static com.exchangeme.helpers.EntityM.ENTITY_MANAGER_FACTORY;

public abstract class AbstractDao<T> implements IDao<T> {

    private final EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
    private EntityTransaction transaction = em.getTransaction();
    private Class<T> aClass;

    @Override
    public List<T> getAll() {
        TypedQuery<T> query = em.createQuery("SELECT u FROM " + aClass.getSimpleName() + " u", aClass);
        return query
                .getResultList();
    }

    @Override
    public Optional<T> getById(Long id) {
        return Optional.ofNullable(em.find(aClass, id));
    }

    @Override
    public void insert(T t) {

    }

    @Override
    public void update(T t) {

    }

    @Override
    public void delete(Long id) {

    }
}
