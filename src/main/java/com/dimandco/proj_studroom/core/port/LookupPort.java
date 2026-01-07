package com.dimandco.proj_studroom.core.port;

import com.dimandco.proj_studroom.core.model.PersonType;

import java.util.Optional;

/**
 * Port to external service for managing lookups.
 */
public interface LookupPort {

    Optional<PersonType> lookup(final String huaId);
}
