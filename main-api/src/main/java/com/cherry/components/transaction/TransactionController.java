package com.cherry.components.transaction;

import com.cherry.components.account.AccountService;
import com.cherry.constants.RESTPaths;
import com.cherry.exception.BadRequestException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RESTPaths.TRANSACTIONS)
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    private AccountService accountService;

    @Operation(summary = "Create a transaction",
            description = "A transaction is a movement of fund from one account to another")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction create(@Valid @RequestBody TransactionDTO entity) {
        Transaction transaction = entity.toTransaction(accountService);
        validateTransaction(transaction);
        return transactionService.create(transaction);
    }

    @GetMapping
    public List<TransactionDTO> getAll(@RequestParam TransactionType type,
                                       @RequestParam Long fromAccountId, @RequestParam Long toAccountId,
                                       @RequestParam Long startTimestamp, @RequestParam Long endTimestamp) {
        return transactionService
                .getTransactions(type, fromAccountId, toAccountId, startTimestamp, endTimestamp)
                .stream().map(Transaction::toDTO).toList();
    }

    @GetMapping(RESTPaths.ID)
    public TransactionDTO getOne(@PathVariable Long id) {
        return transactionService.getById(id).toDTO();
    }

    @PutMapping(RESTPaths.ID)
    public Transaction replace(@Valid @RequestBody TransactionDTO newEntity, @PathVariable Long id) {
        Transaction transaction = newEntity.toTransaction(accountService);
        validateTransaction(transaction);
        return transactionService.replace(transaction, id);
    }

    @DeleteMapping(RESTPaths.ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        transactionService.delete(id);
    }

    private void validateTransaction(Transaction transaction) {
        TransactionType transactionType = transaction.getType();
        if (!transactionType.isValidTransaction(transaction)) {
            throw new BadRequestException(String.format(
                    "Invalid account types for %s transaction. Valid from accounts: %s. Valid to accounts: %s",
                    transactionType, transactionType.getFromAccountTypes(), transactionType.getToAccountTypes()));
        }
    }
}