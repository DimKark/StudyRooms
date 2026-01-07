package com.dimandco.proj_studroom.core.service.model;

import com.dimandco.proj_studroom.core.model.Person;
import com.dimandco.proj_studroom.core.model.PersonType;

/**
 * Simple record for exposing {@link Person} info
 */
public record PersonView (
        long id,
        String huaId,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        PersonType type
) {
    public String fullName() {
        return this.firstName + " " + this.lastName;
    }
}
