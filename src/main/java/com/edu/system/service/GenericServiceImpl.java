package com.edu.system.service;

import com.edu.system.exception.ServiceException;
import com.edu.system.model.AbstractEntity;
import com.edu.system.repository.BaseRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class GenericServiceImpl<T extends AbstractEntity, R extends BaseRepository<T, Integer>> implements GenericService<T> {

    private final R dao;

    protected GenericServiceImpl(R dao) {
        this.dao = dao;
    }

    @Override
    public T getById(Integer id) {
        return dao
                .findById(id)
                .orElseThrow(() -> new ServiceException("Can't get entity by id " + id + " from " + getClass().getSimpleName().replace("ServiceImpl", "")));
    }

    @Override
    @Transactional
    public T save(T entity) {
        return dao.save(entity);
    }

    @Override
    @Transactional
    public List<T> save(List<T> entities) {
        return dao.saveAll(entities);
    }

    @Override
    @Transactional
    public void delete(T entity) {
        dao.delete(entity);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        dao.deleteById(id);
    }

    @Override
    public List<T> getAll() {
        return dao.findAll();
    }

    public R getDao() {
        return dao;
    }
}
