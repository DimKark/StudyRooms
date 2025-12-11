package com.dimandco.proj_studroom;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

/**
 * Implementation of {@link PersonService}
 */
@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonServiceImpl(final PersonRepository personRepository, final PersonMapper personMapper) {
        if (personRepository == null) throw new NullPointerException();
        if (personMapper == null) throw new NullPointerException();

        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    public List<PersonView> getPeople() {
        return List.of(); // TODO Implement
    }

    /**
    @Override
    public CreatePersonResult createPerson(CreatePersonRequest createPersonRequest, boolean notify) {
        return null;
    }
     */

    @Override
    public CreatePersonResult createPerson(final CreatePersonRequest createPersonRequest) {
        if(createPersonRequest == null) throw new NullPointerException();

        // TODO Validate createPersonRequest

        final PersonType type = createPersonRequest.type();
        final String firstName = createPersonRequest.firstName().strip();
        final String lastName = createPersonRequest.lastName().strip();
        final String emailAddress = createPersonRequest.emailAddress().strip();
        final String mobilePhoneNumber = createPersonRequest.mobilePhoneNumber().strip();
        final String rawPassword = createPersonRequest.rawPassword().strip();

        if(!emailAddress.endsWith("@hua.gr"))
            return CreatePersonResult.fail("Email must end in '@hua.gr'");

        // TODO Email must be unique
        // TODO Phone number must be unique

        final String hashedPassword = createPersonRequest.rawPassword().strip(); // TODO Encode password

        Person person = new Person();
        person.setType(type);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setEmailAddress(emailAddress);
        person.setMobilePhoneNumber(mobilePhoneNumber);
        person.setPasswordHash(hashedPassword);

        person = this.personRepository.save(person);

        final PersonView personView = this.personMapper.convertPersonToPersonView(person);

        return CreatePersonResult.success(personView);
    }
}