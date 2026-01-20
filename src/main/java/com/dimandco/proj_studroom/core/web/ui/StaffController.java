package com.dimandco.proj_studroom.core.web.ui;

import com.dimandco.proj_studroom.core.model.RoomReservation;
import com.dimandco.proj_studroom.core.model.StudyRoom;
import com.dimandco.proj_studroom.core.repository.ReservationRepository;
import com.dimandco.proj_studroom.core.repository.StudyRoomRepository;
import com.dimandco.proj_studroom.core.security.CurrentUser;
import com.dimandco.proj_studroom.core.service.StudyRoomService;
import com.dimandco.proj_studroom.core.service.model.CancelReservationRequest;
import com.dimandco.proj_studroom.core.service.model.CreatePersonRequest;
import com.dimandco.proj_studroom.core.service.model.ModifyStudyRoomRequest;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

/**
 * Controller for staff functions
 */
@Controller
public class StaffController {
    private final StudyRoomRepository studyRoomRepository;
    private final StudyRoomService studyRoomService;
    private final ReservationRepository reservationRepository;

    public StaffController(StudyRoomRepository studyRoomRepository,
                           StudyRoomService studyRoomService,
                           ReservationRepository reservationRepository) {
        if (studyRoomRepository == null) throw new NullPointerException("Study room repository cannot be null");
        if (studyRoomService == null) throw new NullPointerException("Study room service cannot be null");
        if (reservationRepository == null) throw new NullPointerException("Reservation repository cannot be null");

        this.studyRoomRepository = studyRoomRepository;
        this.studyRoomService = studyRoomService;
        this.reservationRepository = reservationRepository;
    }

    @GetMapping("/studyRoomList")
    public String displayStudyRooms(final Authentication authentication,
                                    final Model model) {
        if (!AuthUtils.isAuthenticated(authentication)) return "redirect:/login";

        CurrentUser me = (CurrentUser) model.getAttribute("me");
        if (me == null) throw new NullPointerException("Current user is null");
        if (!me.type().toString().equals("STAFF")) return "redirect:/profile";

        List<StudyRoom> rooms = this.studyRoomRepository.findAll();
        if (rooms.isEmpty()) {
            model.addAttribute("error", "There are no rooms");
            return "redirect:/profile";
        }
        model.addAttribute("rooms", rooms);

        return "studyRoomList";
    }

    @GetMapping("/modifyStudyRoom")
    public String modifyRoom(final Authentication authentication,
                             final Model model) {
        if (!AuthUtils.isAuthenticated(authentication)) { return "redirect:/login"; }

        CurrentUser me = (CurrentUser) model.getAttribute("me");
        if (me == null) throw new NullPointerException("Current user is null");
        if (!me.type().toString().equals("STAFF")) {
            model.addAttribute("error", "Only staff can modify rooms");
            return "profile";
        }

        List<StudyRoom> rooms = this.studyRoomRepository.findAll();
        if (rooms.isEmpty()) {
            model.addAttribute("error", "There are no rooms");
            System.out.println("There are no rooms");
            return "profile";
        }

        model.addAttribute("rooms", rooms);
        model.addAttribute("modifyStudyRoomRequest", ModifyStudyRoomRequest.empty());

        return "modifyStudyRoom";
    }

    @PostMapping("modifyStudyRoom")
    public String modifyRoom(final Authentication authentication,
                             @Valid @ModelAttribute("modifyStudyRoomRequest") ModifyStudyRoomRequest modifyStudyRoomRequest,
                             final BindingResult bindingResult, // Important! BindingResult **MUST** come in after the @Valid argument
                             final Model model)
    {
        if (!AuthUtils.isAuthenticated(authentication)) return "redirect:/login";
        if (bindingResult.hasErrors()) return "modifyStudyRoom";

        Optional<StudyRoom> roomOpt = studyRoomRepository.findById(modifyStudyRoomRequest.roomId());
        if (roomOpt.isEmpty()) throw new NullPointerException("Room is null");
        StudyRoom room = roomOpt.get();

        if (modifyStudyRoomRequest.capacity() > 0) room.setCapacity(modifyStudyRoomRequest.capacity());
        else if (modifyStudyRoomRequest.capacity() < 0) {
            model.addAttribute("error", "Capacity cannot be less than 0");
            return "modifyStudyRoom";
        }
        if (modifyStudyRoomRequest.openFrom() != null) room.setOpenFrom(modifyStudyRoomRequest.openFrom());
        if (modifyStudyRoomRequest.openTo() != null) room.setOpenTo(modifyStudyRoomRequest.openTo());
        if (modifyStudyRoomRequest.toggleActive()) room.setActive(!room.getActive());
        studyRoomRepository.save(room);

        model.addAttribute("message", "Room modified");
        return "modifyStudyRoom";
    }

    @GetMapping("/cancelReservation")
    String cancelReservationPage(final Authentication authentication,
                                 final Model model)
    {
        CurrentUser me = (CurrentUser) model.getAttribute("me");
        if (me == null) throw new NullPointerException("Current user is null");
        if (!me.type().toString().equals("STAFF")) {
            model.addAttribute("error", "Only staff can access this page");
            return "profile";
        }

        List<RoomReservation> reservations = reservationRepository.findAll();
        if(reservations.isEmpty()) {
            model.addAttribute("error", "There are no reservations");
            return "profile";
        }

        model.addAttribute("reservations", reservations);
        model.addAttribute("cancelReservationRequest", new CancelReservationRequest(0L));

        return "cancelReservation";
    }

    @PostMapping("/cancelReservation")
    String cancelReservationPage(final Authentication authentication,
                                 @Valid @ModelAttribute("cancelReservationRequest")
                                 CancelReservationRequest cancelReservationRequest,
                                 final BindingResult bindingResult, // Important! BindingResult **MUST** come in after the @Valid argument
                                 final Model model)
    {
        if (!AuthUtils.isAuthenticated(authentication)) return "redirect:/login";
        if (bindingResult.hasErrors()) return "modifyStudyRoom";

        RoomReservation reservation =
                reservationRepository.findById(cancelReservationRequest.id()).orElse(null);
        if(reservation == null) throw new NullPointerException("Reservation is null");

        reservation.cancelReservation();
        reservationRepository.save(reservation);

        model.addAttribute("message", "Reservation cancelled");
        return "cancelReservation";
    }
}
