package com.dimandco.proj_studroom;

import java.util.List;

/**
 * Service for managing students/ teachers
 */
public interface PersonService {

    List<PersonView> getPeople();

    CreatePersonResult createPerson(CreatePersonRequest createPersonRequest, final boolean notify);

    default CreatePersonResult createPerson(final CreatePersonRequest createPersonRequest) {
        return this.createPerson(createPersonRequest, true);
    }
}
