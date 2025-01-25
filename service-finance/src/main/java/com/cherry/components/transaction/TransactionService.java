package com.cherry.components.transaction;

import com.cherry.components.BaseService;
import com.cherry.components.pagination.CustomPage;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class TransactionService extends BaseService<Transaction, Long> {
    @Getter(AccessLevel.PROTECTED)
    @Autowired
    private TransactionRepository repository;

    @Override
    protected Class<Transaction> getEntityClass() {
        return Transaction.class;
    }

    public CustomPage<TransactionDTO> getTransactions(Pageable pageable, TransactionType type,
                                                      Long fromAccountId, Long toAccountId,
                                                      Long startTimestampInclusive, Long endTimestampInclusive) {
        Specification<Transaction> specification = Specification
                .where(TransactionSpecification.hasType(type))
                .and(TransactionSpecification.hasFromAccount(fromAccountId))
                .and(TransactionSpecification.hasToAccount(toAccountId))
                .and(TransactionSpecification.createdAfter(startTimestampInclusive))
                .and(TransactionSpecification.createdBefore(endTimestampInclusive));
        return new CustomPage<>(repository.findAll(specification, pageable).map(Transaction::toDTO));
    }
}