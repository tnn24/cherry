package com.cherry.components.transaction;

import com.cherry.components.BaseEntity;
import com.cherry.components.account.Account;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
public class Transaction implements BaseEntity<Transaction> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Transaction type is required")
    private TransactionType type;

    @NotNull(message = "From account is required")
    @ManyToOne
    @JoinColumn(name = "from_id", referencedColumnName = "id")
    private Account fromAccount;
    @NotNull(message = "To account is required")
    @ManyToOne
    @JoinColumn(name = "to_id", referencedColumnName = "id")
    private Account toAccount;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount mist be larger than 0")
    private Double amount;
    @NotNull(message = "Timestamp is required")
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