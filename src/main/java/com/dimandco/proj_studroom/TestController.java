package com.dimandco.proj_studroom;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

/**
 * Used for testing
 */
@RestController
public class TestController {
    private final PersonRepository personRepository;

    public TestController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping(value = "/test", produces = MediaType.TEXT_PLAIN_VALUE)
    public String test() {
        Person person = new Person();
        person.setId(null); // auto-generated
        person.setEmailAddress("example@hua.gr");
        person.setFirstName("ExampleFirstName");
        person.setLastName("ExampleLastName");
        person.setMobilePhoneNumber("+306900000000");
        person.setCreatedAt(Instant.now());

        person = this.personRepository.save(person);

        return person.toString();
    }
}
