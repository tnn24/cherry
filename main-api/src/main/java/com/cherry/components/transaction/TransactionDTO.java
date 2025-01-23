package com.cherry.components.transaction;

import com.cherry.components.account.AccountService;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(name = TransactionConstant.TABLE_NAME)
@Data
@Accessors(chain = true)
public class TransactionDTO {
    @JsonProperty(TransactionConstant.JSON_KEY_ID)
    private Long id;

    @NotNull(message = TransactionConstant.ERROR_TYPE_REQUIRED)
    @JsonProperty(TransactionConstant.JSON_KEY_TYPE)
    private TransactionType type;

    @NotNull(message = TransactionConstant.ERROR_FROM_ACCOUNT_REQUIRED)
    @JsonProperty(TransactionConstant.JSON_KEY_FROM_ACCOUNT)
    private Long fromAccount;
    @NotNull(message = TransactionConstant.ERROR_TO_ACCOUNT_REQUIRED)
    @JsonProperty(TransactionConstant.JSON_KEY_TO_ACCOUNT)
    private Long toAccount;

    @NotNull(message = TransactionConstant.ERROR_AMOUNT_REQUIRED)
    @Positive(message = TransactionConstant.ERROR_AMOUNT_POSITIVE)
    @JsonProperty(TransactionConstant.JSON_KEY_AMOUNT)
    private Double amount;
    @NotNull(message = TransactionConstant.ERROR_TIMESTAMP_REQUIRED)
    @JsonProperty(TransactionConstant.JSON_KEY_TIMESTAMP)
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