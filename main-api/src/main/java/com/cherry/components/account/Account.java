package com.cherry.components.account;

import com.cherry.components.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
public class Account implements BaseEntity<Account> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private AccountType type;

    @Column(nullable = false, unique = true)
    private String name;

    @Override
    public Account replace(Account newEntity) {
        return setName(newEntity.getName())
                .setType(newEntity.getType());
    }
}