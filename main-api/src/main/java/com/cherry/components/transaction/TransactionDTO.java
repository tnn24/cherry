package com.cherry.components.transaction;

import com.cherry.components.account.AccountService;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(name = TransactionTable.TABLE_NAME)
@Data @Accessors(chain = true)
public class TransactionDTO {
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