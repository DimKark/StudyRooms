package com.dimandco.proj_studroom.core.web.ui;

import com.dimandco.proj_studroom.core.model.Person;
import com.dimandco.proj_studroom.core.repository.PersonRepository;
import com.dimandco.proj_studroom.core.security.CurrentUser;
import com.dimandco.proj_studroom.core.service.ReservationService;
import com.dimandco.proj_studroom.core.service.model.CreateReservationRequest;
import com.dimandco.proj_studroom.core.service.model.CreateReservationResult;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReservationController {
    private final ReservationService reservationService;
    private final PersonRepository personRepository;

    public ReservationController(ReservationService reservationService,
                                 PersonRepository personRepository) {
        if(reservationService == null) throw new NullPointerException();
        if(personRepository == null) throw new NullPointerException();
        this.reservationService = reservationService;
        this.personRepository = personRepository;
    }

    @GetMapping("/reservation")
    public String showReservationForm(final Authentication authentication,
                                      final Model model) {
        if(!AuthUtils.isAuthenticated(authentication)) {
            model.addAttribute("error",
                    "You are not logged in. Please log in before making a reservation");
            return "redirect:/login";
        }

        CreateReservationRequest crr = CreateReservationRequest.empty();
        model.addAttribute("createReservationRequest", crr);

        return "reservation";
    }

    @PostMapping("/reservation")
    public String handleReservationSubmission(
        @Valid @ModelAttribute("createReservationRequest") CreateReservationRequest createReservationRequest,
        final BindingResult bindingResult, // Important! BindingResult **MUST** come in after the @Valid argument
        final Model model
    ) {
        if(bindingResult.hasErrors()) return "redirect:/login";

        CurrentUser currentUser = (CurrentUser) model.getAttribute("me");
        Long studentId = currentUser.id();
        Person student = personRepository.findById(studentId).orElse(null);
        if(student == null) throw new NullPointerException("Student is null");

        if(createReservationRequest.from().isAfter(createReservationRequest.to())) {
            model.addAttribute("error", "Invalid time period");
            return "reservation";
        }

        CreateReservationRequest newCrr = CreateReservationRequest.withStudent(createReservationRequest, student);
        CreateReservationResult createReservationResult =
                this.reservationService.createReservation(newCrr);

        if(createReservationResult.created()) {
            // Show success message
            model.addAttribute("message", "Reservation has been created");
            return "reservation";
        }

        // Retain form data on page reload
        model.addAttribute("createReservationRequest", createReservationRequest);

        //Show Error Message
        model.addAttribute("errorMessage", createReservationResult.reason());

        return "reservation";
    }
}
