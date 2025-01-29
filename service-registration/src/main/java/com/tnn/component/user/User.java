package com.tnn.component.user;

import com.tnn.component.BaseEntity;
import com.tnn.component.family.Family;
import com.tnn.component.family.FamilyConstant;
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
    @Column(name = UserConstant.COLUMN_ID)
    private long id;

    @Column(name = UserConstant.COLUMN_FIRST_NAME)
    private String firstName;
    @Column(name = UserConstant.COLUMN_LAST_NAME)
    private String lastName;
    @Column(name = UserConstant.COLUMN_EMAIL)
    private String email;

    @Column(name = UserConstant.COLUMN_ROLE)
    private UserRole role;
    @ManyToOne
    @JoinColumn(name = UserConstant.COLUMN_FAMILY, referencedColumnName = FamilyConstant.COLUMN_ID)
    private Family family;

    @Column(name = UserConstant.COLUMN_PASSWORD)
    private String password;

    @Override
    public User replace(User newEntity) {
        return setFirstName(newEntity.getFirstName())
                .setLastName(newEntity.getLastName())
                .setEmail(newEntity.getEmail())
                .setRole(newEntity.getRole());
    }
}