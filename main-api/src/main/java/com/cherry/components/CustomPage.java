package com.cherry.components;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class CustomPage<E> {
    public static final String JSON_KEY_CONTENT = "content";
    public static final String JSON_KEY_TOTAL_ELEMENTS = "totalElements";
    public static final String JSON_KEY_TOTAL_PAGES = "totalPages";
    public static final String JSON_KEY_CURRENT_PAGE = "currentPage";
    public static final String JSON_KEY_PAGE_SIZE = "pageSize";

    private final List<E> content;
    private final long totalElements;
    private final int totalPages;
    private final int currentPage;
    private final int pageSize;

    public CustomPage(Page<E> page) {
        content = page.getContent();
        totalElements = page.getTotalElements();
        totalPages = page.getTotalPages();
        currentPage = page.getNumber();
        pageSize = page.getSize();
    }
}