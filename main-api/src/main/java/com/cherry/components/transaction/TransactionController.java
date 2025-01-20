package com.cherry.components.transaction;

import com.cherry.constants.RESTPaths;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RESTPaths.TRANSACTIONS)
public class TransactionController {
    @Autowired
    private TransactionService service;

    @Operation(summary = "Create a transaction",
            description = "A transaction is a movement of fund from one account to another")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction create(@RequestBody Transaction entity) {
        return service.create(entity);
    }

    @GetMapping
    public List<Transaction> getAll() {
        return service.list();
    }

    @GetMapping(RESTPaths.ID)
    public Transaction getOne(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping(RESTPaths.ID)
    public Transaction replace(@RequestBody Transaction newEntity, @PathVariable Long id) {
        return service.replace(newEntity, id);
    }

    @DeleteMapping(RESTPaths.ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}