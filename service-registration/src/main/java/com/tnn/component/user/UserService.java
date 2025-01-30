package com.tnn.component.user;

import com.tnn.component.BaseService;
import com.tnn.exception.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, Long> {
    @Autowired
    @Getter(AccessLevel.PROTECTED)
    private UserRepository repository;
    @Autowired
    private PasswordService passwordService;

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public User create(User entity) {
        return super.create(entity.setPassword(passwordService.encryptPassword(entity.getPassword())));
    }

    public boolean authenticate(String email, String rawPassword) {
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Email not found: " + email));
        return passwordService.validatePassword(rawPassword, user.getPassword());
    }
}