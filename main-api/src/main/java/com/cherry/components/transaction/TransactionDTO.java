package com.cherry.components.transaction;

import com.cherry.components.BaseEntity;
import com.cherry.components.account.AccountService;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TransactionDTO implements BaseEntity<TransactionDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Transaction type is required")
    private TransactionType type;

    @NotNull(message = "From account is required")
    private Long fromAccount;
    @NotNull(message = "To account is required")
    private Long toAccount;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount mist be larger than 0")
    private Double amount;
    @NotNull(message = "Timestamp is required")
    private Long timestamp;

    @Override
    public TransactionDTO replace(TransactionDTO newEntity) {
        return setFromAccount(newEntity.getFromAccount())
                .setToAccount(newEntity.getToAccount())
                .setAmount(newEntity.getAmount())
                .setTimestamp(newEntity.getTimestamp());
    }

    public Transaction toTransaction(AccountService accountService) {
        return new Transaction()
                .setId(getId())
                .setType(getType())
                .setFromAccount(accountService.getById(getFromAccount()))
                .setToAccount(accountService.getById(getToAccount()))
                .setAmount(getAmount())
                .setTimestamp(getTimestamp());
    }
}