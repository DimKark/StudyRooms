package com.dimandco.proj_studroom;

import com.dimandco.proj_studroom.Person;
import com.dimandco.proj_studroom.CreatePersonRequest;
import com.dimandco.proj_studroom.PersonView;
import java.util.List;

public interface PersonService {

    CreatePersonResult createPerson(final CreatePersonRequest createPersonRequest, final boolean notify);

    default CreatePersonResult createPerson(final CreatePersonRequest createPersonRequest) {
        return this.createPerson(createPersonRequest, true);
    }
}
