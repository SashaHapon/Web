package org.food.api.repository;

import java.util.List;

public interface GenericDao<T> {

    T create(T entity);

    T findById(int id);

    T update(T entity);

    void delete(T entity);

    List<T> findAll();
}