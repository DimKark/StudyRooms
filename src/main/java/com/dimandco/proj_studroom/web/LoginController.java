package com.dimandco.proj_studroom.web;

import com.dimandco.proj_studroom.PersonRepository;

public class LoginController {

    private final PersonRepository personRepository;

    public LoginController(PersonRepository personRepository) {
        if (personRepository == null) throw new NullPointerException();
        this.personRepository = personRepository;
    }

    //@GetMapping("/login")

}
