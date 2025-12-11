package com.dimandco.proj_studroom;

import java.util.List;

/**
 * Service for managing students/ teachers
 */
public interface PersonService {

    List<PersonView> getPeople();

    CreatePersonResult createPerson(CreatePersonRequest createPersonRequest);
}
