package com.dimandco.proj_studroom.port;


import com.dimandco.proj_studroom.Person;
import com.dimandco.proj_studroom.PersonType;

import java.util.Optional;

/**
 * Port to external service for managing lookups.
 */
public interface LookupPort {

    Optional<PersonType> lookup(final String huaId);

}
