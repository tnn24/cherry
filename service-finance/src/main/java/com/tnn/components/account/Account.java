package com.tnn.components.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tnn.component.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(name = AccountConstant.TABLE_NAME)
@Data
@Accessors(chain = true)
@Entity
@Table(name = AccountConstant.TABLE_NAME)
public class Account implements BaseEntity<Account> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = AccountConstant.COLUMN_ID)
    @JsonProperty(AccountConstant.JSON_KEY_ID)
    private Long id;

    @NotNull(message = AccountConstant.ERROR_TYPE_REQUIRED)
    @Column(name = AccountConstant.COLUMN_TYPE)
    @JsonProperty(AccountConstant.JSON_KEY_TYPE)
    private AccountType type;

    @NotBlank(message = AccountConstant.ERROR_NAME_REQUIRED)
    @Size(min = AccountConstant.VALID_NAME_LENGTH_MIN,
            max = AccountConstant.VALID_NAME_LENGTH_MAX,
            message = AccountConstant.ERROR_NAME_LENGTH_VALID)
    @Pattern(regexp = AccountConstant.VALID_NAME_PATTERN,
            message = AccountConstant.ERROR_NAME_PATTERN_VALID)
    @Column(name = AccountConstant.COLUMN_NAME, nullable = false, unique = true)
    @JsonProperty(AccountConstant.JSON_KEY_NAME)
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