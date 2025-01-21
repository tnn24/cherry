package com.cherry.components.transaction;

import com.cherry.components.BaseService;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService extends BaseService<Transaction, Long> {
    @Getter(AccessLevel.PROTECTED)
    @Autowired
    private TransactionRepository repository;

    @Override
    protected Class<Transaction> getEntityClass() {
        return Transaction.class;
    }

    public List<Transaction> getTransactions(TransactionType type, Long fromAccountId, Long toAccountId,
                                             Long startTimestamp, Long endTimestamp) {
        Specification<Transaction> specification = Specification
                .where(TransactionSpecification.hasType(type))
                .and(TransactionSpecification.hasFromAccount(fromAccountId))
                .and(TransactionSpecification.hasToAccount(toAccountId))
                .and(TransactionSpecification.createdAfter(startTimestamp))
                .and(TransactionSpecification.createdBefore(endTimestamp));
        return repository.findAll(specification);
    }
}