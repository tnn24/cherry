package com.cherry.components.transaction;

import com.cherry.constants.RESTPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RESTPaths.TRANSACTIONS)
public class TransactionController {
    @Autowired
    private TransactionService service;

    @PostMapping
    public Transaction create(@RequestBody Transaction entity) {
        return service.save(entity);
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
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}