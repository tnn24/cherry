package com.cherry.components.account;

import com.cherry.constants.RESTPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RESTPaths.ACCOUNTS)
public class AccountController {
    @Autowired
    private AccountService service;

    @PostMapping
    public Account create(@RequestBody Account entity) {
        return service.save(entity);
    }

    @GetMapping
    public List<Account> getAll() {
        return service.list();
    }

    @GetMapping(RESTPaths.ID)
    public Account getOne(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping(RESTPaths.ID)
    public Account replace(@RequestBody Account newEntity, @PathVariable Long id) {
        return service.replace(newEntity, id);
    }

    @DeleteMapping(RESTPaths.ID)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}