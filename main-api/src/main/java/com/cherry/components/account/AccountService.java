package com.cherry.components.account;

import com.cherry.components.BaseService;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService extends BaseService<Account, Long> {
    @Autowired
    @Getter(AccessLevel.PROTECTED)
    private AccountRepository repository;

    @Override
    protected String isEntityValid(Account entity) {
        return "";
    }
}