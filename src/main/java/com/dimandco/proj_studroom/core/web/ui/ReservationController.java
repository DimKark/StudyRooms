package com.dimandco.proj_studroom.core.web.ui;

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

    public ReservationController(ReservationService reservationService) {
        if(reservationService == null) throw new NullPointerException();
        this.reservationService = reservationService;
    }

    @GetMapping("/reservation")
    public String showReservationForm(final Authentication authentication,
                                      final Model model) {
        if(!AuthUtils.isAuthenticated(authentication)) {
            model.addAttribute("error",
                    "You are not logged in. Please log in before making a reservation");
            return "redirect:/login";
        }

        model.addAttribute("createReservationRequest", CreateReservationRequest.empty());

        return "reservation";
    }

    @PostMapping("/reservation")
    public String handleReservationSubmission(
        @Valid @ModelAttribute("createReservationRequest") CreateReservationRequest createReservationRequest,
        final BindingResult bindingResult, // Important! BindingResult **MUST** come in after the @Valid argument
        final Model model
    ) {
        if(bindingResult.hasErrors()) return "redirect:/login";

        CreateReservationResult createReservationResult = this.reservationService.createReservation(createReservationRequest);

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
