package com.cherry.components;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty(JSON_KEY_CONTENT)
    private final List<E> content;
    @JsonProperty(JSON_KEY_TOTAL_ELEMENTS)
    private final long totalElements;
    @JsonProperty(JSON_KEY_TOTAL_PAGES)
    private final int totalPages;
    @JsonProperty(JSON_KEY_CURRENT_PAGE)
    private final int currentPage;
    @JsonProperty(JSON_KEY_PAGE_SIZE)
    private final int pageSize;

    public CustomPage(Page<E> page) {
        content = page.getContent();
        totalElements = page.getTotalElements();
        totalPages = page.getTotalPages();
        currentPage = page.getNumber();
        pageSize = page.getSize();
    }
}