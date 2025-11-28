package com.dimandco.proj_studroom;

import com.dimandco.proj_studroom.Person;
import com.dimandco.proj_studroom.CreatePersonRequest;
import com.dimandco.proj_studroom.PersonView;
import java.util.List;

public interface PersonService {
    list<PersonView> getPeople();

    PersonView createper(CreatePersonRequest createpereq);
}
