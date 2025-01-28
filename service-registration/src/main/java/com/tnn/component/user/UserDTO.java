package com.tnn.component.user;

import com.tnn.component.family.FamilyService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDTO {
    private long id;

    private String firstName;
    private String lastName;
    @Email
    private String email;

    @NotNull
    private UserRole role;
    private long familyId;

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