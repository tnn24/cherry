package com.cherry.components.account;

import com.cherry.constants.RESTPaths;
import com.cherry.exception.BadRequestException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(RESTPaths.ACCOUNTS)
public class AccountController {
    @Autowired
    private AccountService service;

    @Operation(summary = "Create a new account",
            description = "Create a financial account. " +
                    "It can be an account from a bank, a stock broker, a vendor, an employer, or a tax")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account create(@Valid @RequestBody Account entity) {
        return service.create(entity);
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
    public Account replace(@Valid @RequestBody Account newEntity, @PathVariable Long id) {
        if (!Objects.equals(newEntity.getId(), id)) {
            throw new BadRequestException(String.format(AccountConstant.ERROR_REPLACE_ID, id, newEntity.getId()));
        }
        return service.replace(newEntity, id);
    }

    @DeleteMapping(RESTPaths.ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}