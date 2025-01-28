package com.tnn.component.user;

import com.tnn.component.BaseEntity;
import com.tnn.component.family.Family;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
public class User implements BaseEntity<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String middleName;
    private String lastName;
    @Email
    private String email;

    @NotNull
    private UserRole role;
    @ManyToOne
    private Family family;

    @Override
    public User replace(User newEntity) {
        return null;
    }
}