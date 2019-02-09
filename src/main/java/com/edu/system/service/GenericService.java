package com.edu.system.service;

import com.edu.system.model.AbstractEntity;

import java.util.List;

public interface GenericService<T extends AbstractEntity> {
    T getById(Integer id);

    T save(T entity);

    T update(T entity);

    List<T> save(List<T> entities);

    void delete(T entity);

    void deleteById(Integer id);

    List<T> getAll();
}
