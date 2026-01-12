package com.dimandco.proj_studroom.core.service.mapper;

import com.dimandco.proj_studroom.core.model.Person;
import com.dimandco.proj_studroom.core.service.model.PersonView;
import org.springframework.stereotype.Component;

/**
 * Mapper to convert {@link Person} to {@link PersonView}
 */
@Component
public class PersonMapper {

    public PersonView convertPersonToPersonView(final Person person) {
        if (person == null) {
            return null;
        }

        final PersonView personView = new PersonView (
                person.getId(),
                person.getHuaId(),
                person.getFirstName(),
                person.getLastName(),
                person.getMobilePhoneNumber(),
                person.getEmailAddress(),
                person.getType()
        );

        return personView;
    }
}
