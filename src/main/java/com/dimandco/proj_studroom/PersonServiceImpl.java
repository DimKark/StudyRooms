package com.dimandco.proj_studroom;

import com.dimandco.proj_studroom.port.LookupPort;
import com.dimandco.proj_studroom.port.SmsNotificationPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

/**
 * Implementation of {@link PersonService}
 * commented out some things for when we are ready to implement sms notification
 */
@Service
public class PersonServiceImpl implements PersonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);

    private final PasswordEncoder passwordEncoder;
    private final LookupPort lookupPort;
    private final SmsNotificationPort smsNotificationPort;
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonServiceImpl(final PasswordEncoder passwordEncoder,
                             final LookupPort lookupPort,
                             final SmsNotificationPort smsNotificationPort,
                             final PersonRepository personRepository,
                             final PersonMapper personMapper) {
        if (passwordEncoder == null) throw new NullPointerException();
        if (lookupPort == null) throw new IllegalArgumentException();
        if (smsNotificationPort == null) throw new IllegalArgumentException();
        if (personRepository == null) throw new NullPointerException();
        if (personMapper == null) throw new NullPointerException();

        this.passwordEncoder = passwordEncoder;
        this.lookupPort = lookupPort;
        this.smsNotificationPort = smsNotificationPort;
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
        final String huaId = createPersonRequest.huaId().strip();
        final String firstName = createPersonRequest.firstName().strip();
        final String lastName = createPersonRequest.lastName().strip();
        final String emailAddress = createPersonRequest.emailAddress().strip();
        final String mobilePhoneNumber = createPersonRequest.mobilePhoneNumber().strip();
        final String rawPassword = createPersonRequest.rawPassword().strip();

        // ------------------------------------------

        if(!emailAddress.endsWith("@hua.gr"))
            return CreatePersonResult.fail("Email must end in '@hua.gr'");

        if (this.personRepository.existsByHuaIdIgnoreCase(huaId)) {
            return CreatePersonResult.fail("Hua Id must be unique");
        }

        if (this.personRepository.existsByEmailAddressIgnoreCase(emailAddress)) {
            return CreatePersonResult.fail("Email address must be unique");
        }

        if (this.personRepository.existsByMobilePhoneNumber(mobilePhoneNumber)) {
            return CreatePersonResult.fail("Mobile Phone number must be unique");
        }

        // -------------------------------------------

        // Use external service to validate Hua ID and role
        final PersonType personType$Lookup = this.lookupPort.lookup(huaId).orElse(null);
        if (personType$Lookup == null) {
            return CreatePersonResult.fail("Invalid HUA ID");
        }
        if (personType$Lookup != type) {
            return CreatePersonResult.fail("The provided person type does not match the actual one");
        }

        // -------------------------------------------

        final String hashedPassword = this.passwordEncoder.encode(rawPassword);

        Person person = new Person();
        person.setType(type);
        person.setHuaId(huaId);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setEmailAddress(emailAddress);
        person.setMobilePhoneNumber(mobilePhoneNumber);
        person.setPasswordHash(hashedPassword);

        person = this.personRepository.save(person);

        // ----------------------------

        final String content = String.format("You have successfully registered for Study Rooms application. " +
                "Use your email (%s) to login.", emailAddress);
        final boolean sent = this.smsNotificationPort.sendSms(mobilePhoneNumber, content);
        if (!sent) {
            LOGGER.warn("SMS sent to {} failed", mobilePhoneNumber);
        }


        final PersonView personView = this.personMapper.convertPersonToPersonView(person);

        return CreatePersonResult.success(personView);
    }
}