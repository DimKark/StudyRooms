package com.dimandco.proj_studroom;

import org.springframework.stereotype.Component;
// mapper for {@Link Person} to {@Link PersonView)
@Component
public class PersonMapper {

    public PersonView convertPersonToPersonView(final Person person) {
        if (person == null) {
            return null;
        }
        final PersonView personView = new PersonView (
                person.getId(),
                person.getFirstName(),
                person.getLastName(),
                person.getPhoneNumber(),
                person.getEmail(),
                person.getType()
        );
        return personView;
    }
}
