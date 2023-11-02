package org.food.api.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

public interface Dao<T> {

    T create(T entity);

    T findById(int id);

    T update(T entity);

    void delete(T entity);

    List<T> findAll();
}