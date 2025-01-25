package com.tnn.component.pagination;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Getter
public class CustomPage<E> {

    public static Pageable toPageable(int page, int size, String sort) {
        return PageRequest.of(page, size, Sort.by(sort.split(",")));
    }

    @JsonProperty(PaginationConstant.JSON_KEY_CONTENT)
    private final List<E> content;
    @JsonProperty(PaginationConstant.JSON_KEY_TOTAL_ELEMENTS)
    private final long totalElements;
    @JsonProperty(PaginationConstant.JSON_KEY_TOTAL_PAGES)
    private final int totalPages;
    @JsonProperty(PaginationConstant.JSON_KEY_CURRENT_PAGE)
    private final int currentPage;
    @JsonProperty(PaginationConstant.JSON_KEY_PAGE_SIZE)
    private final int pageSize;

    public CustomPage(Page<E> page) {
        content = page.getContent();
        totalElements = page.getTotalElements();
        totalPages = page.getTotalPages();
        currentPage = page.getNumber();
        pageSize = page.getSize();
    }
}