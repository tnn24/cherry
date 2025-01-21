package com.cherry.components.transaction;

import com.cherry.components.BaseEntity;
import com.cherry.components.account.Account;
import com.cherry.components.account.AccountTable;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(hidden = true)
@Data @Accessors(chain = true)
@Entity
@Table(name = TransactionTable.TABLE_NAME)
public class Transaction implements BaseEntity<Transaction> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = TransactionTable.COLUMN_ID)
    private Long id;

    @Column(name = TransactionTable.COLUMN_TYPE)
    private TransactionType type;

    @ManyToOne
    @JoinColumn(name = TransactionTable.COLUMN_FROM_ACCOUNT,
            referencedColumnName = AccountTable.COLUMN_ID)
    private Account fromAccount;
    @ManyToOne
    @JoinColumn(name = TransactionTable.COLUMN_TO_ACCOUNT,
            referencedColumnName = AccountTable.COLUMN_ID)
    private Account toAccount;

    @Column(name = TransactionTable.COLUMN_AMOUNT)
    private Double amount;
    @Column(name = TransactionTable.COLUMN_TIMESTAMP)
    private Long timestamp;

    @Override
    public Transaction replace(Transaction newEntity) {
        return setFromAccount(newEntity.getFromAccount())
                .setToAccount(newEntity.getToAccount())
                .setAmount(newEntity.getAmount())
                .setTimestamp(newEntity.getTimestamp());
    }

    public TransactionDTO toDTO() {
        return new TransactionDTO()
                .setId(getId())
                .setType(getType())
                .setFromAccount(getFromAccount().getId())
                .setToAccount(getToAccount().getId())
                .setAmount(getAmount())
                .setTimestamp(getTimestamp());
    }
}