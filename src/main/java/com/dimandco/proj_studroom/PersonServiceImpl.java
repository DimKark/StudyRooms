package com.dimandco.proj_studroom;

import com.dimandco.proj_studroom.Person;
import com.dimandco.proj_studroom.PersonService;
import com.dimandco.proj_studroom.CreatePersonRequest;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.util.List;

//should be the implementation for the person created that handles the stuff or something - alex

public final class PersonServiceImpl implements PersonService {
    @Service
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonServiceImpl(final PersonRepository personRepository, final PersonMapper personMapper) {
        if (personRepository == null) throw new NullPointerException();
        if (personMapper == null) throw new NullPointerException();

        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    }
    @Override
    public list<PersonView> getPeople() {
        return list.of();
    }

    @Override
    public CreatePersonResult createPersonResult(final CreatePersonRequest createpereq) {
        if (createpereq == null) throw new NullPointerException();

        //[doubt] unpack the b*tch - after validation of course

        final PersonType type = createpereq.type();
        final String id = createpereq.id().strip();
        final String firstName = createpereq.firstName().strip();
        final String lastName = createpereq.lastName().strip();
        final String email = createpereq.email().strip();
        final String phoneNumber = createpereq.phoneNumber().strip();

        //TODO id, email, and phoneNumber have to be unique
        if (!email.endsWith("@hua.gr")) {
            return CreatePersonResult.fail("only academic emails are allowed. sorry.");
        }

        //TODO use an external service for id validation
        // and encode a raw password to hash
        final String hashedPass = rawPass;

        // initiating person
        Person person = new Person();
        person.setId(null); //auto generated
        person.setType(type);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setEmail(email);
        person.setPhoneNumber(phoneNumber);
        person.setCreatedAt(null);

        person = this.personRepository.save(person);

        final PersonView personView = this.personMapper.convertPersonToPersonView(person);

        return CreatePersonResult.success(personView);
    }
}
