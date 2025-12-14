package com.dimandco.proj_studroom.web;

import com.dimandco.proj_studroom.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private final PersonRepository personRepository;

    public LoginController(PersonRepository personRepository) {
        if (personRepository == null) throw new NullPointerException();
        this.personRepository = personRepository;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
