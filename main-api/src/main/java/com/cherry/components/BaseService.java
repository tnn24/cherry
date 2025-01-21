package com.cherry.components;

import com.cherry.exception.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class BaseService<T extends BaseEntity<T>, ID> {

    public T create(T entity) {
        return getRepository().save(entity);
    }

    public List<T> list() {
        return getRepository().findAll();
    }

    public T getById(ID id) {
        return getRepository().findById(id)
                .orElseThrow(() -> new EntityNotFoundException(getEntityClass(), id));
    }

    public T replace(T newEntity, ID id) {
        return getRepository().findById(id)
                .map(e -> getRepository().save(e.replace(newEntity)))
                .orElseThrow(() -> new EntityNotFoundException(getEntityClass(), id));
    }

    public void delete(ID id) {
        getRepository().deleteById(id);
    }

    protected abstract <R extends JpaRepository<T, ID>> R getRepository();
    protected abstract Class<T> getEntityClass();
}