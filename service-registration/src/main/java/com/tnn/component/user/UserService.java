package com.tnn.component.user;

import com.tnn.component.BaseService;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, Long> {
    @Autowired
    @Getter(AccessLevel.PROTECTED)
    private UserRepository repository;

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}