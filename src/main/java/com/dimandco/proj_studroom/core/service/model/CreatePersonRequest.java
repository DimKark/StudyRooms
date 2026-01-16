package com.dimandco.proj_studroom.core.service.model;

import com.dimandco.proj_studroom.core.model.Person;
import com.dimandco.proj_studroom.core.model.PersonType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Simple data type to transfer {@link Person} data between classes
 */
public record CreatePersonRequest(
        @NotNull PersonType type,
        @NotNull @NotBlank @Size(max = 20) String huaId,
        @NotNull @NotBlank @Size(max = 100) String firstName,
        @NotNull @NotBlank @Size(max = 100) String lastName,
        @NotNull @NotBlank @Size(max = 100) @Email String emailAddress,
        @NotNull @NotBlank @Size(max = 18) String mobilePhoneNumber,
        @NotNull @NotBlank @Size(max = 100) @Size(min = 4, max = 24) String rawPassword
) {
    public static CreatePersonRequest empty() {
        return new CreatePersonRequest(PersonType.STUDENT,
                "", "", "", "", "",
                "");
    }
}
