package com.tnn.component.family;

import com.tnn.component.BaseService;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FamilyService extends BaseService<Family, Long> {
    @Autowired
    @Getter(AccessLevel.PROTECTED)
    private FamilyRepository repository;

    @Override
    protected Class<Family> getEntityClass() {
        return Family.class;
    }
}