package com.dimandco.proj_studroom.web;

import com.dimandco.proj_studroom.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final PersonService personService;

    public RegistrationController(final PersonService personService) {
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
    public String handleFormSubmission(@ModelAttribute("person") Person person, @Valid @ModelAttribute("createPersonRequest") final CreatePersonRequest createPersonRequest, final Model model) {
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
        final CreatePersonResult createPersonResult = this.personService.createPerson(createPersonRequest);
        if (createPersonResult.created()) {
            return "redirect:/login"; // registration is successfull, yay!!1!!1!!1
        }
        model.addAttribute("createPersonRequest", createPersonRequest); // Pass the same form data.
        model.addAttribute("errorMessage", createPersonResult.reason()); // Show an error message!
        return "register";
    }
}