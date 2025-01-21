package com.cherry.components.account;

import com.cherry.components.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
public class Account implements BaseEntity<Account> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Account type is required")
    private AccountType type;

    @NotBlank(message = "Account name is required")
    @Size(min = 1, max = 100, message = "Account name must be between 1 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$\n", message = "Account name must only contain letters and numbers")
    @Column(nullable = false, unique = true)
    private String name;

    @Override
    public Account replace(Account newEntity) {
        return setName(newEntity.getName())
                .setType(newEntity.getType());
    }

    @PrePersist @PreUpdate
    private void cleanUpFields() {
        if (getName() != null) {
            setName(getName().trim());
        }
    }
}