package com.cherry.config;

import com.cherry.constants.Misc;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

@Configuration
public class PaginationConfig implements PageableHandlerMethodArgumentResolverCustomizer {
    @Override
    public void customize(PageableHandlerMethodArgumentResolver resolver) {
        resolver.setMaxPageSize(Misc.PAGE_SIZE_MAX);
    }
}