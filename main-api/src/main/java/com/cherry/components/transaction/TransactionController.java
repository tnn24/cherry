package com.cherry.components.transaction;

import com.cherry.constants.RESTPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService service;

    @PostMapping(RESTPaths.TRANSACTIONS)
    public Transaction create(@RequestBody Transaction entity) {
        return service.save(entity);
    }

    @GetMapping(RESTPaths.TRANSACTIONS)
    public List<Transaction> getAll() {
        return service.list();
    }

    @GetMapping(RESTPaths.TRANSACTION_ID)
    public Transaction getOne(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping(RESTPaths.TRANSACTION_ID)
    public Transaction replace(@RequestBody Transaction newEntity, @PathVariable Long id) {
        return service.replace(newEntity, id);
    }

    @DeleteMapping(RESTPaths.TRANSACTION_ID)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}