package com.cherry.components.transaction;

import com.cherry.components.BaseEntity;
import com.cherry.components.account.Account;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
public class Transaction implements BaseEntity<Transaction> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TransactionType type;

    @ManyToOne
    @JoinColumn(name = "from_id", referencedColumnName = "id")
    private Account fromAccount;
    @ManyToOne
    @JoinColumn(name = "to_id", referencedColumnName = "id")
    private Account toAccount;

    private Double amount;
    private Long time;

    public Transaction(TransactionType type) {
        this.type = type;
    }

    @Override
    public Transaction replace(Transaction newEntity) {
        return setAmount(newEntity.getAmount())
                .setTime(newEntity.getTime());
    }
}