package com.edu.system.repository;

import com.edu.system.model.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T extends AbstractEntity, ID extends Serializable> extends JpaRepository<T, ID> {

    Optional<T> findById(ID id);
}
