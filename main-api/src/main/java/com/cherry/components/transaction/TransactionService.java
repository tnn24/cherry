package com.cherry.components.transaction;

import com.cherry.components.BaseService;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService extends BaseService<Transaction, Long> {
    @Getter(AccessLevel.PROTECTED)
    @Autowired
    private TransactionRepository repository;

    @Override
    protected String isEntityValid(Transaction entity) {
        return "";
    }
}