package com.tnn.components.transaction;

import com.tnn.components.RESTPath;
import com.tnn.components.account.AccountService;
import com.tnn.exception.BadRequestException;
import com.tnn.pagination.CustomPage;
import com.tnn.pagination.PaginationConstant;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RESTPath.TRANSACTIONS)
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
    public CustomPage<TransactionDTO> getAll(
            @RequestParam(required = false, value = PaginationConstant.PARAM_KEY_PAGE,
                    defaultValue = PaginationConstant.DEFAULT_PAGE_NUM) int page,
            @RequestParam(required = false, value = PaginationConstant.PARAM_KEY_SIZE,
                    defaultValue = PaginationConstant.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(required = false, value = PaginationConstant.PARAM_KEY_SORT,
                    defaultValue = TransactionConstant.COLUMN_ID) String sort,
            @RequestParam(required = false) TransactionType type,
            @RequestParam(required = false) Long fromAccountId,
            @RequestParam(required = false) Long toAccountId,
            @RequestParam(required = false) Long startTimeInclusive,
            @RequestParam(required = false) Long endTimeInclusive) {
        return transactionService.getTransactions(CustomPage.toPageable(page, size, sort),
                type, fromAccountId, toAccountId, startTimeInclusive, endTimeInclusive);
    }

    @GetMapping(RESTPath.ID)
    public TransactionDTO getOne(@PathVariable Long id) {
        return transactionService.getById(id).toDTO();
    }

    @PutMapping(RESTPath.ID)
    public Transaction replace(@Valid @RequestBody TransactionDTO newEntity, @PathVariable Long id) {
        Transaction transaction = newEntity.toTransaction(accountService);
        validateTransaction(transaction);
        return transactionService.replace(transaction, id);
    }

    @DeleteMapping(RESTPath.ID)
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