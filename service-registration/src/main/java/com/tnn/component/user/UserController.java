package com.tnn.component.user;

import com.tnn.component.RESTPath;
import com.tnn.component.family.FamilyService;
import com.tnn.component.user.User;
import com.tnn.component.user.UserConstant;
import com.tnn.exception.BadRequestException;
import com.tnn.pagination.CustomPage;
import com.tnn.pagination.PaginationConstant;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(RESTPath.USER)
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private FamilyService familyService;

    @Operation(summary = "Create a new user")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody UserDTO entity) {
        return service.create(entity.toUser(familyService));
    }

    @GetMapping
    public CustomPage<User> getAll(
            @RequestParam(required = false, value = PaginationConstant.PARAM_KEY_PAGE,
                    defaultValue = PaginationConstant.DEFAULT_PAGE_NUM) int page,
            @RequestParam(required = false, value = PaginationConstant.PARAM_KEY_SIZE,
                    defaultValue = PaginationConstant.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(required = false, value = PaginationConstant.PARAM_KEY_SORT,
                    defaultValue = UserConstant.JSON_KEY_ID) String sort) {
        return service.findAll(CustomPage.toPageable(page, size, sort));
    }

    @GetMapping(RESTPath.ID)
    public User getOne(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping(RESTPath.ID)
    public User replace(@Valid @RequestBody User newEntity, @PathVariable Long id) {
        if (!Objects.equals(newEntity.getId(), id)) {
            throw new BadRequestException(String.format(UserConstant.ERROR_REPLACE_ID, id, newEntity.getId()));
        }
        return service.replace(newEntity, id);
    }

    @DeleteMapping(RESTPath.ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}