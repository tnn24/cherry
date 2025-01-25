package com.tnn.components.account;

import com.tnn.component.BaseService;
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
    protected Class<Account> getEntityClass() {
        return Account.class;
    }
}