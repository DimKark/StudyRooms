package com.dimandco.proj_studroom.core.service;

import com.dimandco.proj_studroom.core.service.model.CreatePersonRequest;
import com.dimandco.proj_studroom.core.service.model.CreatePersonResult;
import com.dimandco.proj_studroom.core.service.model.PersonView;

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
