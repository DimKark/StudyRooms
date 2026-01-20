package com.dimandco.proj_studroom.core.web;

import com.dimandco.proj_studroom.core.model.RoomReservation;
import com.dimandco.proj_studroom.core.model.StudyRoom;
import com.dimandco.proj_studroom.core.repository.PersonRepository;
import com.dimandco.proj_studroom.core.model.Person;
import com.dimandco.proj_studroom.core.model.PersonType;
import com.dimandco.proj_studroom.core.repository.ReservationRepository;
import com.dimandco.proj_studroom.core.repository.StudyRoomRepository;
import com.dimandco.proj_studroom.core.service.PersonService;
import com.dimandco.proj_studroom.core.service.ReservationService;
import com.dimandco.proj_studroom.core.service.StudyRoomService;
import com.dimandco.proj_studroom.core.service.impl.ReservationServiceImpl;
import com.dimandco.proj_studroom.core.service.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Controller for <strong>testing</strong>
 */
@Controller
public class TestController {
    private final PersonService personService;
    private final PersonRepository personRepository;
    private final ReservationService reservationService;
    private final ReservationRepository reservationRepository;
    private final StudyRoomService studyRoomService;
    private final StudyRoomRepository studyRoomRepository;

    public TestController(
            PersonService personService,
            ReservationService reservationService,
            PersonRepository personRepository,
            ReservationRepository reservationRepository,
            StudyRoomService studyRoomService,
            StudyRoomRepository studyRoomRepository) {
        if(personService == null) throw new NullPointerException();
        if(reservationService == null) throw new NullPointerException();
        if(personRepository == null) throw new NullPointerException();
        if(reservationRepository == null) throw new NullPointerException();
        if(studyRoomService == null) throw new NullPointerException();
        if(studyRoomRepository == null) throw new NullPointerException();

        this.personService = personService;
        this.reservationService = reservationService;
        this.personRepository = personRepository;
        this.reservationRepository = reservationRepository;
        this.studyRoomService = studyRoomService;
        this.studyRoomRepository = studyRoomRepository;
    }

    @GetMapping(value = "test/error/NullPointerException")
    public String testErrorNullPointerException() {
        final Integer a = null;
        final int b = 0;
        final int c = a + b; // Throws NullPointerException
        return null; // Unreachable
    }

    @GetMapping("test/reservation")
    public String testDb(Model m) {
        if(m == null) throw new NullPointerException();

        createRooms();

        CreatePersonRequest cpr = new CreatePersonRequest(
                PersonType.STUDENT,
                "it2023107",
                "Test",
                "User",
                "test@hua.gr",
                "6945280161",
                "1234"
        );
        CreatePersonResult r = this.personService.createPerson(cpr);
        if(!r.created()) System.out.println(r.reason());

        cpr = new CreatePersonRequest(
                PersonType.STAFF,
                "sr1111111",
                "Test",
                "Staff",
                "1@hua.gr",
                "99999999999",
                "1234"
        );
        r = this.personService.createPerson(cpr);
        if(!r.created()) System.out.println(r.reason());

        return "login";
    }

    private void createRooms() {
        CreateStudyRoomRequest csrr = new CreateStudyRoomRequest(
                "Room 1-1",
                10,
                LocalTime.of(10, 0),
                LocalTime.of(20, 0),
                true
        );
        this.studyRoomService.createStudyRoom(csrr);

        csrr = new CreateStudyRoomRequest(
                "Room 1-2",
                        15,
                LocalTime.of(8, 0),
                LocalTime.of(21, 0),
                        true
        );
        this.studyRoomService.createStudyRoom(csrr);

        csrr = new CreateStudyRoomRequest(
                "Room 2-1",
                7,
                LocalTime.of(9, 0),
                LocalTime.of(19, 0),
                false
        );
        this.studyRoomService.createStudyRoom(csrr);
    }

    public List<RoomReservation> getRoomReservations(Long id) {
        return  this.reservationRepository.findAllByRoom_Id(id);
    }
}
