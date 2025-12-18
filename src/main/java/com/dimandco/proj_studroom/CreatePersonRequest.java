package com.dimandco.proj_studroom;

import java.time.Instant;

/**
 * Simple data type to transfer {@link Person} data between classes
 */
public record CreatePersonRequest(
        PersonType type,
        String huaId,
        String firstName,
        String lastName,
        String emailAddress,
        String mobilePhoneNumber,
        String rawPassword
) {}
