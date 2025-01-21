package com.cherry.components.transaction;

import com.cherry.components.account.AccountType;
import lombok.Getter;

import java.util.Set;

@Getter
public enum TransactionType {
    PURCHASE(
            Set.of(AccountType.CASH, AccountType.INCOME),
            Set.of(AccountType.VENDOR, AccountType.TAX)),
    REFUND(
            Set.of(AccountType.VENDOR, AccountType.TAX),
            Set.of(AccountType.CASH, AccountType.INCOME)),
    TRANSFER(
            Set.of(AccountType.CASH, AccountType.BROKERAGE, AccountType.INCOME),
            Set.of(AccountType.CASH, AccountType.BROKERAGE));

    private final Set<AccountType> fromAccountTypes;
    private final Set<AccountType> toAccountTypes;

    TransactionType(Set<AccountType> fromAccountTypes, Set<AccountType> toAccountTypes) {
        this.fromAccountTypes = fromAccountTypes;
        this.toAccountTypes = toAccountTypes;
    }

    public boolean isValidTransaction(Transaction transaction) {
        return fromAccountTypes.contains(transaction.getFromAccount().getType())
                && toAccountTypes.contains(transaction.getToAccount().getType());
    }
}