package com.tnn.component.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tnn.component.family.FamilyService;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDTO {
    @JsonProperty(UserConstant.JSON_KEY_ID)
    private long id;

    @NotBlank(message = UserConstant.ERROR_FIRST_NAME_REQUIRED)
    @Size(min = UserConstant.VALID_NAME_LENGTH_MIN,
            max = UserConstant.VALID_NAME_LENGTH_MAX,
            message = UserConstant.ERROR_FIRST_NAME_LENGTH_VALID)
    @Pattern(regexp = UserConstant.VALID_NAME_PATTERN,
            message = UserConstant.ERROR_FIRST_NAME_PATTERN_VALID)
    @JsonProperty(UserConstant.JSON_KEY_FIRST_NAME)
    private String firstName;
    @NotBlank(message = UserConstant.ERROR_LAST_NAME_REQUIRED)
    @Size(min = UserConstant.VALID_NAME_LENGTH_MIN,
            max = UserConstant.VALID_NAME_LENGTH_MAX,
            message = UserConstant.ERROR_LAST_NAME_LENGTH_VALID)
    @Pattern(regexp = UserConstant.VALID_NAME_PATTERN,
            message = UserConstant.ERROR_LAST_NAME_PATTERN_VALID)
    @JsonProperty(UserConstant.JSON_KEY_LAST_NAME)
    private String lastName;
    @NotBlank(message = UserConstant.ERROR_EMAIL_REQUIRED)
    @Email(message = UserConstant.ERROR_EMAIL_PATTERN_VALID)
    @JsonProperty(UserConstant.JSON_KEY_EMAIL)
    private String email;

    @NotNull
    @JsonProperty(UserConstant.JSON_KEY_ROLE)
    private UserRole role;
    @JsonProperty(UserConstant.JSON_KEY_FAMILY)
    private long familyId;

    @NotBlank(message = UserConstant.ERROR_PASSWORD_REQUIRED)
    @Pattern(regexp = UserConstant.VALID_PASSWORD_PATTERN,
            message = UserConstant.ERROR_PASSWORD_PATTERN_VALID)
    @JsonProperty(UserConstant.JSON_KEY_PASSWORD)
    private String password;

    public User toUser(FamilyService service) {
        return new User()
                .setId(getId())
                .setFirstName(getFirstName())
                .setLastName(getLastName())
                .setEmail(getEmail())
                .setRole(getRole())
                .setFamily(service.getById(getFamilyId()));
    }
}