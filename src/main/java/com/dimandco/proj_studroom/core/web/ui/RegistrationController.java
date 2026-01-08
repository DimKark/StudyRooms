package com.dimandco.proj_studroom.core.web.ui;

import com.dimandco.proj_studroom.*;
import com.dimandco.proj_studroom.core.model.Person;
import com.dimandco.proj_studroom.core.model.PersonType;
import com.dimandco.proj_studroom.core.service.model.CreatePersonRequest;
import com.dimandco.proj_studroom.core.service.model.CreatePersonResult;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String showRegistrationForm(final Authentication authentication, final Model model) {
        if (AuthUtils.isAuthenticated(authentication)){
            return "redirect:/profile";
        }
        final Person person = new Person();
        model.addAttribute("createPersonRequest", new CreatePersonRequest(PersonType.STUDENT,
                "", "", "", "", "",
                ""));
        return "register"; // This loads register.html
    }


    @PostMapping("/register")
    public String handleFormSubmission(
            final Authentication authentication,
            @Valid @ModelAttribute("createPersonRequest") CreatePersonRequest createPersonRequest,
            final BindingResult bindingResult, // Important! BindingResult **MUST** come in after the @Valid argument
            final Model model
    ) {
        if (AuthUtils.isAuthenticated(authentication)){
            return "redirect:/profile";
        }
        if (bindingResult.hasErrors()){
            return "register";
        }

        /**
        final String emailAddress = person.getEmailAddress();
        final String mobilePhoneNumber = person.getMobilePhoneNumber();
        final String huaId = person.getHuaId();

        if (this.personRepository.existsByEmailAddressIgnoreCase(emailAddress)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email address already exists!");
        }

        if (this.personRepository.existsByMobilePhoneNumber(mobilePhoneNumber)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "mobile phone number already exists");
        }

        if (this.personRepository.existsByHuaId(huaId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "HUA ID already exists");
        }*/
        /**
        final CreatePersonResult createPersonResult = this.personService.createPerson(createPersonRequest);
        if (createPersonResult.created()) {
            return "redirect:/login"; // registration is successfull, yay!!1!!1!!1
        }
        model.addAttribute("createPersonRequest", createPersonRequest); // Pass the same form data.
        model.addAttribute("errorMessage", createPersonResult.reason()); // Show an error message!
        return "register";
        */
        final CreatePersonResult createPersonResult = this.personService.createPerson(createPersonRequest);

        if(createPersonResult.created()){
            return "redirect:/login";
        }

        // Retain form data on page reload
        model.addAttribute("createPersonRequest", createPersonRequest);

        //Show Error Message
        model.addAttribute("errorMessage", createPersonResult.reason());

        return "register";
    }
}