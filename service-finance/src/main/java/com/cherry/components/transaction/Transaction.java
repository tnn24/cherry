package com.cherry.components.transaction;

import com.cherry.components.BaseEntity;
import com.cherry.components.account.Account;
import com.cherry.components.account.AccountConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(hidden = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = TransactionConstant.TABLE_NAME)
public class Transaction implements BaseEntity<Transaction> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = TransactionConstant.COLUMN_ID)
    private Long id;

    @Column(name = TransactionConstant.COLUMN_TYPE)
    private TransactionType type;

    @ManyToOne
    @JoinColumn(name = TransactionConstant.COLUMN_FROM_ACCOUNT, referencedColumnName = AccountConstant.COLUMN_ID)
    private Account fromAccount;
    @ManyToOne
    @JoinColumn(name = TransactionConstant.COLUMN_TO_ACCOUNT, referencedColumnName = AccountConstant.COLUMN_ID)
    private Account toAccount;

    @Column(name = TransactionConstant.COLUMN_AMOUNT)
    private Double amount;
    @Column(name = TransactionConstant.COLUMN_TIMESTAMP)
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