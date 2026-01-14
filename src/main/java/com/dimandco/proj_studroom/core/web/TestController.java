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
import com.dimandco.proj_studroom.core.service.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalTime;

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

    @GetMapping("test/db")
    public String testDb() {
        CreateStudyRoomRequest csrr = new CreateStudyRoomRequest(
                "testRoom",
                10,
                LocalTime.now(),
                LocalTime.now(),
                true
        );
        CreateStudyRoomResult csrres = this.studyRoomService.createStudyRoom(csrr);
        StudyRoom sr = new StudyRoom();
        if(csrres.created()) {
            sr = studyRoomRepository.findById(csrres.studyRoomView().id()).get();
        }

        System.out.println(sr.toString());

        CreatePersonRequest cpr = new CreatePersonRequest(
                PersonType.STUDENT,
                "it2023107",
                "Test",
                "User",
                "test@hua.gr",
                "6945280161",
                "1234"
        );
        CreatePersonResult cpRes = this.personService.createPerson(cpr);
        Person p = new Person();
        if(cpRes.created()) {
            p = personRepository.findById(cpRes.personView().id()).get();
        }

        CreateReservationRequest crr = new CreateReservationRequest(
                sr,
                p,
                LocalDate.now(),
                LocalTime.now(),
                LocalTime.now()
        );
        CreateReservationResult crres = this.reservationService.createReservation(crr);
        RoomReservation rr = new RoomReservation();
        if(crres.created()) {
            rr = reservationRepository.findById(crres.reservationView().id()).get();
        }

        return "test";
    }
}
