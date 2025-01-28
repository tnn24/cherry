package com.tnn.exception;

import com.tnn.component.BaseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Class<? extends BaseEntity<?>> entityClazz, Object id) {
        super(entityClazz.getSimpleName() + " id not found: " + id);
    }
}