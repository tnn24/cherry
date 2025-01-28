package com.tnn.component;

import com.tnn.pagination.CustomPage;
import com.tnn.exception.EntityNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseService<T extends BaseEntity<T>, ID> {

    public T create(T entity) {
        return getRepository().save(entity);
    }

    public CustomPage<T> findAll(Pageable pageable) {
        return new CustomPage<>(getRepository().findAll(pageable));
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