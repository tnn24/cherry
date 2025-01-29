package com.tnn.component.family;

import com.tnn.component.RESTPath;
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
@RequestMapping(RESTPath.FAMILY)
public class FamilyController {
    @Autowired
    private FamilyService service;

    @Operation(summary = "Create a new family")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Family create(@Valid @RequestBody Family entity) {
        return service.create(entity);
    }

    @GetMapping
    public CustomPage<Family> getAll(
            @RequestParam(required = false, value = PaginationConstant.PARAM_KEY_PAGE,
                    defaultValue = PaginationConstant.DEFAULT_PAGE_NUM) int page,
            @RequestParam(required = false, value = PaginationConstant.PARAM_KEY_SIZE,
                    defaultValue = PaginationConstant.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(required = false, value = PaginationConstant.PARAM_KEY_SORT,
                    defaultValue = FamilyConstant.JSON_KEY_ID) String sort) {
        return service.findAll(CustomPage.toPageable(page, size, sort));
    }

    @GetMapping(RESTPath.ID)
    public Family getOne(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping(RESTPath.ID)
    public Family replace(@Valid @RequestBody Family newEntity, @PathVariable Long id) {
        if (!Objects.equals(newEntity.getId(), id)) {
            throw new BadRequestException(String.format(FamilyConstant.ERROR_REPLACE_ID, id, newEntity.getId()));
        }
        return service.replace(newEntity, id);
    }

    @DeleteMapping(RESTPath.ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}