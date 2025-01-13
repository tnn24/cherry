package com.cherry.components.transaction;

import com.cherry.components.BaseService;
import com.cherry.exception.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService extends BaseService<Transaction, Long> {
    @Getter(AccessLevel.PROTECTED)
    @Autowired
    private TransactionRepository repository;

    @Override
    protected EntityNotFoundException getEntityNotFoundException(Long id) {
        return new EntityNotFoundException(Transaction.class, id);
    }
}