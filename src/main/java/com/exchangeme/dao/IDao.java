package com.exchangeme.dao;

import java.util.List;
import java.util.Optional;

public interface IDao<T> {

    List<T> getAll();
    Optional<T> getById(Long id);
    void insert(T t);
    void update(T t);
    void delete(Long id);
}
