package com.tnn.components.account;

import com.tnn.pagination.CustomPage;
import com.tnn.pagination.PaginationConstant;
import com.tnn.components.RESTPath;
import com.tnn.exception.BadRequestException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(RESTPath.ACCOUNTS)
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
    public CustomPage<Account> getAll(
            @RequestParam(required = false, value = PaginationConstant.PARAM_KEY_PAGE,
                    defaultValue = PaginationConstant.DEFAULT_PAGE_NUM) int page,
            @RequestParam(required = false, value = PaginationConstant.PARAM_KEY_SIZE,
                    defaultValue = PaginationConstant.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(required = false, value = PaginationConstant.PARAM_KEY_SORT,
                    defaultValue = AccountConstant.JSON_KEY_ID) String sort) {
        return service.findAll(CustomPage.toPageable(page, size, sort));
    }

    @GetMapping(RESTPath.ID)
    public Account getOne(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping(RESTPath.ID)
    public Account replace(@Valid @RequestBody Account newEntity, @PathVariable Long id) {
        if (!Objects.equals(newEntity.getId(), id)) {
            throw new BadRequestException(String.format(AccountConstant.ERROR_REPLACE_ID, id, newEntity.getId()));
        }
        return service.replace(newEntity, id);
    }

    @DeleteMapping(RESTPath.ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}