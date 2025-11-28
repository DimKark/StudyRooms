package com.dimandco.proj_studroom.web;

import com.dimandco.proj_studroom.Person;
import com.dimandco.proj_studroom.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;

public class LoginController {

    private final PersonRepository personRepository;

    public RegistrationController(PersonRepository personRepository) {
        if (personRepository == null) throw new NullPointerException();
        this.personRepository = personRepository;
    }

    @GetMapping("/login")

}
