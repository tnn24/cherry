package com.tnn.component.family;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tnn.component.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(name = FamilyConstant.TABLE_NAME)
@Data
@Accessors(chain = true)
@Entity
@Table(name = FamilyConstant.TABLE_NAME)
public class Family implements BaseEntity<Family> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = FamilyConstant.COLUMN_ID)
    @JsonProperty(FamilyConstant.JSON_KEY_ID)
    private long id;

    @NotBlank(message = FamilyConstant.ERROR_NAME_REQUIRED)
    @Size(min = FamilyConstant.VALID_NAME_LENGTH_MIN,
            max = FamilyConstant.VALID_NAME_LENGTH_MAX,
            message = FamilyConstant.ERROR_NAME_LENGTH_VALID)
    @Pattern(regexp = FamilyConstant.VALID_NAME_PATTERN,
            message = FamilyConstant.ERROR_NAME_PATTERN_VALID)
    @Column(name = FamilyConstant.COLUMN_NAME, nullable = false)
    @JsonProperty(FamilyConstant.COLUMN_NAME)
    private String name;

    @Override
    public Family replace(Family newEntity) {
        return setName(newEntity.getName());
    }
}