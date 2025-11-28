package com.dimandco.proj_studroom.web;

import com.dimandco.proj_studroom.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final PersonService personRepository;

    public RegistrationController(PersonService personService) {
        if (personService == null) throw new NullPointerException();
        this.personService = personService;
    }

    // Show registration form
    @GetMapping("/register")
    public String showRegistrationForm(final Model model) {
        // todo if user is auth, redirect to default view.
        final Person person = new Person();
        model.addAttribute("person", new CreatePersonRequest(PersonType.STUDENT, "", "", "", "", ""));
        return "register"; // This loads register.html
    }


    @PostMapping("/register")
    public String handleFormSubmission(@ModelAttribute("person") Person person) {
        // TODO if form has errors, show form + errors
        // TODO if form is okay, store person, redirect.

        //final String emailAddress = person.getEmailAddress();
        //final String mobilePhoneNumber = person.getMobilePhoneNumber();
        //final String huaId = person.getHuaId();

        //if (this.personRepository.existsByEmailAddressIgnoreCase(emailAddress)) {
        //    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email address already exists!");
        //}

        //if (this.personRepository.existsByMobilePhoneNumber(mobilePhoneNumber)) {
        //    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "mobile phone number already exists");
        //}

        //if (this.personRepository.existsByHuaId(huaId)) {
        //    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "HUA ID already exists");
        //}

        person = this.personRepository.save(person);
        System.out.println(person.toString());
        return "register";
    }
}